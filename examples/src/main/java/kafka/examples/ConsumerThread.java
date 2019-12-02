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
import kafka.avro_serde.CustomAvroDeserializer;
import kafka.protobuf_serde.CustomProtobufDeserializer;
import kafka.protobuf_serde.generated.PbClasses;
import kafka.utils.ShutdownableThread;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DecoderFactory;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.protocol.Message;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

public class ConsumerThread extends ShutdownableThread {
    private Consumer consumer;
    private final String topic;
    private final SerializerType serializerType;

    public ConsumerThread(String topic, SerializerType serializerType) {
        super("KafkaConsumerExample", false);
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.KAFKA_SERVER_URL + ":" + KafkaProperties.KAFKA_SERVER_PORT);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "DemoConsumer");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");

        // TODO: Check what this one actually does. I am a bit dubious about it -- Sahil
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");

        switch (serializerType) {
            case AVRO:
                // TODO: Init the custom avro deserializer
                consumer = new KafkaConsumer<>(props, new IntegerDeserializer(), new CustomAvroDeserializer(Utilities.primitiveMessageSchema));
                break;
            case PB:
                consumer = new KafkaConsumer<>(props, new IntegerDeserializer(), new CustomProtobufDeserializer<>(PbClasses.PrimitiveMessage.parser()));
                break;
            case DEFAULT:
                consumer = new KafkaConsumer<>(props, new IntegerDeserializer(), new StringDeserializer());
                break;
        }

//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArrayDeserializer");

        this.topic = topic;
        this.serializerType = serializerType;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void doWork() {
//        String userSchema = "{\"type\":\"record\"," +
//                "\"name\":\"myrecord\"," +
//                "\"fields\":[{\"name\":\"f1\",\"type\":\"string\"}]}";
//        Schema.Parser parser = new Schema.Parser();
//        Schema schema = parser.parse(userSchema);

        try {
            consumer.subscribe(Collections.singletonList(this.topic));
            Map<MetricName, Metric> metricMap = null;

            // TODO: This is an ugly switch statement, perhaps there is something better? -- Sahil
            switch (this.serializerType){
                case PB:
                    ConsumerRecords<Integer, MessageLite> records = consumer.poll(Duration.ofSeconds(1));
                    for (ConsumerRecord<Integer, MessageLite> record : records) {
                        System.out.println("===========Received message: (" + record.key() + ") at offset " + record.offset() + "===========");
                        metricMap = consumer.metrics();
                        for( MetricName m_name : metricMap.keySet()){
                            Metric m = metricMap.get(m_name);
                            System.out.println(m.metricName().name() + ": \t" + m.metricValue().toString());
                        }
                    }

                    break;
                case AVRO:
                    ConsumerRecords<Integer, GenericRecord> avro_records = consumer.poll(Duration.ofSeconds(1));
                    for(ConsumerRecord<Integer, GenericRecord> avro_r : avro_records){
                        System.out.println("=========== RECVD MESSAGE: (" + avro_r.key() + ") at offset " + avro_r.offset() + "============");
                        metricMap = consumer.metrics();
                        for( MetricName m_name : metricMap.keySet()){
                            Metric m = metricMap.get(m_name);
                            System.out.println(m.metricName().name() + ": \t" + m.metricValue().toString());
                        }
                    }

                    break;
                case DEFAULT:
                    metricMap = consumer.metrics();
                    for( MetricName m_name : metricMap.keySet()){
                        Metric m = metricMap.get(m_name);
                        System.out.println(m_name.toString());
                    }
                    break;
            }
        } catch (WakeupException e) {
            // Ignore for shutdown
        } catch (SerializationException s) {
            System.out.println("Caught a deserialization exception");
        } finally {
            // Shutdown the consumer
            System.out.println("Closing the consumer as well ...");
        }
    }
}
