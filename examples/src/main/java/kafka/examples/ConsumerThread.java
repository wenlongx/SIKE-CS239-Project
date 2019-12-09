/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kafka.examples;

import com.google.protobuf.MessageLite;
import kafka.Utilities;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import kafka.avro_serde.AvroSchemas;
import kafka.avro_serde.CustomAvroDeserializer;
import kafka.capnproto_serde.CustomCapnProtoDeserializer;
import kafka.protobuf_serde.CustomProtobufDeserializer;
import kafka.protobuf_serde.generated.PbClasses;
import kafka.thrift_serde.CustomThriftDeserializer;
import kafka.utils.ShutdownableThread;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.thrift.TBase;
import org.capnproto.MessageReader;

import java.io.File;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class ConsumerThread extends ShutdownableThread {
    private Consumer consumer;
    private final String topic;
    private final SerializerType serializerType;
    private final int iterations;
    private int currIteration;
    private String filename;

    private List<String> consumerMetricsToRecord = Arrays.asList(
            "records-consumed-rate",
            "records-lag-max",
            "outgoing-byte-rate",
            "request-rate",
            "records-lag-avg"
    );

    public ConsumerThread(String topic, SerializerType serializerType, int iterations) {
        super("KafkaConsumerExample", true);
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.KAFKA_SERVER_URL + ":" + KafkaProperties.KAFKA_SERVER_PORT);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "DemoConsumer");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");

        // TODO: Check what this one actually does. I am a bit dubious about it -- Sahil
        // Apparently it must be >= 6000 since that is the zookeeper connection timeout
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "10000");

        // Clear previous results
        this.filename = serializerType.toString() + "_" + iterations + "_consumermetrics.txt";
//        File consumermetrics = new File(this.filename);
//        if (consumermetrics.exists()) {
//            consumermetrics.delete();
//        }

        switch (serializerType) {
            case AVRO1:
                consumer = new KafkaConsumer<>(props, new IntegerDeserializer(), new CustomAvroDeserializer(AvroSchemas.primitiveMessageSchema, serializerType, iterations));
                break;
            case AVRO2:
                consumer = new KafkaConsumer<>(props, new IntegerDeserializer(), new CustomAvroDeserializer(AvroSchemas.complexMessageSchema, serializerType, iterations));
                break;
            case AVRO3:
                consumer = new KafkaConsumer<>(props, new IntegerDeserializer(), new CustomAvroDeserializer(AvroSchemas.nestedMessageSchema, serializerType, iterations));
                break;
            case PB1:
                consumer = new KafkaConsumer<>(props, new IntegerDeserializer(), new CustomProtobufDeserializer<>(PbClasses.PrimitiveMessage.parser(), serializerType, iterations));
                break;
            case PB2:
                consumer = new KafkaConsumer<>(props, new IntegerDeserializer(), new CustomProtobufDeserializer<>(PbClasses.ComplexMessage.parser(), serializerType, iterations));
                break;
            case PB3:
                consumer = new KafkaConsumer<>(props, new IntegerDeserializer(), new CustomProtobufDeserializer<>(PbClasses.NestedMessage.parser(), serializerType, iterations));
                break;
            case CAPNPROTO1:
            case CAPNPROTO2:
            case CAPNPROTO3:
                consumer = new KafkaConsumer<>(props, new IntegerDeserializer(), new CustomCapnProtoDeserializer(serializerType, iterations));
                break;
            case AVRO_SCHEMAREG1:
            case AVRO_SCHEMAREG2:
            case AVRO_SCHEMAREG3:
                props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
                props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
//                props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, "true");
                props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
                consumer = new KafkaConsumer<>(props);
                break;
            case THRIFT1:
            case THRIFT2:
            case THRIFT3:
                consumer = new KafkaConsumer<>(props, new IntegerDeserializer(), new CustomThriftDeserializer(serializerType, iterations));
                break;
        }

        this.topic = topic;
        this.serializerType = serializerType;
        this.iterations = iterations;
        this.currIteration = 0;
        System.out.println("Starting the consumer ...");
    }

    public HashMap<String, String> metricsFromRecord(Map<MetricName, Metric> metricMap) {
        HashMap<String, String> metrics = new HashMap<String, String>();

        // Loop through all the metrics we record (can't just look it up bc it looks it up by an object reference)
        for (MetricName m_name : metricMap.keySet()) {
            Metric m = metricMap.get(m_name);

            if (consumerMetricsToRecord.contains(m.metricName().name())) {

                // records-lag-max
                if ((m.metricName().name().equals("records-lag-max") && !m.metricName().tags().containsKey("partition")) ||
                        // outgoing-byte-rate
                        (m.metricName().name().equals("outgoing-byte-rate") && m.metricName().group().equals("consumer-metrics")) ||
                        // records-lag-avg
                        (m.metricName().name().equals("records-lag-avg")) ||
                        // request-rate
                        (m.metricName().name().equals("request-rate") && m.metricName().group().equals("consumer-metrics")) ||
                        // records-consumed-rate
                        (m.metricName().name().equals("records-consumed-rate") && !m.metricName().tags().containsKey("topic"))

                ) {
                    metrics.put(m.metricName().name().toString(), m.metricValue().toString());
                }
                else {
                    // do nothing
                }

//                System.out.println(m.metricName().name() + ": \t" + m.metricValue().toString() + ": \t" + m.metricName().group() + ": \t" + m.metricName().description() + ": \t" + m.metricName().tags());
//                System.out.println(m.metricName().name() + ": \t" + m.metricValue().toString() + " \t" + m.toString());
            }
        }

        return metrics;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void doWork() {
        try {
            consumer.subscribe(Collections.singletonList(this.topic));
            Map<MetricName, Metric> metricMap = null;
            ArrayList<HashMap<String, String>> allMetrics = new ArrayList<HashMap<String, String>>();

            switch (this.serializerType) {
                case PB1:
                case PB2:
                case PB3:
                    ConsumerRecords<Integer, MessageLite> records = consumer.poll(Duration.ofSeconds(10));
                    for (ConsumerRecord<Integer, MessageLite> record : records) {
                        this.currIteration++;
//                        System.out.println("===========Received message: (" + record.value().toString() + ") at offset " + record.offset() + "===========");

                        metricMap = consumer.metrics();
                        HashMap<String, String> metrics = this.metricsFromRecord(metricMap);
                        allMetrics.add(metrics);
                    }

                    break;
                case AVRO_SCHEMAREG1:
                case AVRO_SCHEMAREG2:
                case AVRO_SCHEMAREG3:
                case AVRO1:
                case AVRO2:
                case AVRO3:
                    ConsumerRecords<Integer, GenericRecord> avroRecords = consumer.poll(Duration.ofSeconds(10));
                    for (ConsumerRecord<Integer, GenericRecord> record : avroRecords) {
                        this.currIteration++;

                        metricMap = consumer.metrics();
                        HashMap<String, String> metrics = this.metricsFromRecord(metricMap);
                        allMetrics.add(metrics);
                    }
                    break;
                case CAPNPROTO1:
                case CAPNPROTO2:
                case CAPNPROTO3:
                    ConsumerRecords<Integer, MessageReader> capnprotoRecords = consumer.poll(Duration.ofSeconds(10));
                    for (ConsumerRecord<Integer, MessageReader> record : capnprotoRecords) {
                        this.currIteration++;

                        metricMap = consumer.metrics();
                        HashMap<String, String> metrics = this.metricsFromRecord(metricMap);
                        allMetrics.add(metrics);
                    }
                    break;
                case THRIFT1:
                case THRIFT2:
                case THRIFT3:
                    ConsumerRecords<Integer, TBase> thriftRecords = consumer.poll(Duration.ofSeconds(10));
                    for (ConsumerRecord<Integer, TBase> record : thriftRecords) {
                        this.currIteration++;

                        metricMap = consumer.metrics();
                        HashMap<String, String> metrics = this.metricsFromRecord(metricMap);
                        allMetrics.add(metrics);
                    }
                    break;
            }

            // Write the per-record metrics to a file
            for (HashMap<String, String> recordMetrics : allMetrics) {
                // This converts the map into a json object
                String result = "{" + recordMetrics.entrySet().stream()
                        .map(e -> "\"" + e.getKey() + "\":" + e.getValue())
                        .collect(Collectors.joining(",")) + "}";
                Utilities.appendStringToFile(this.filename, result);
            }

        } catch (WakeupException e) {
            // Ignore for shutdown
        } catch (SerializationException s) {
            s.printStackTrace();
            System.out.println("Caught a deserialization exception");
        } finally {
            // Close the consumer and shutdown the thread
            if (this.currIteration >= this.iterations) {
                System.out.println("Closing the consumer ...");
                consumer.close();
                this.shutdown();
            }
        }
    }
}
