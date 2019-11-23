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

import kafka.protobuf_serde.CustomProtobufDeserializer;
import kafka.protobuf_serde.generated.PbClasses;
import kafka.utils.ShutdownableThread;
import org.apache.avro.Schema;
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
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerTest extends ShutdownableThread {
    private final Consumer consumer;
    private final String topic;

    public ConsumerTest(String topic, SerializerType serializerType) {
        super("KafkaConsumerExample", false);
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.KAFKA_SERVER_URL + ":" + KafkaProperties.KAFKA_SERVER_PORT);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "DemoConsumer");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");

        // TODO: Check what this one actually does. I am a bit dubious about it -- Sahil
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");

        // Key will always be an int
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());

        switch (serializerType) {
            case DEFAULT:
                props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
                break;
            case PB:
                props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CustomProtobufDeserializer.class.getName());
                break;
        }

//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaAvroDeserializer");
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArrayDeserializer");
//        props.put("schema.registry.url", "http://localhost:8081");

        consumer = new KafkaConsumer<>(props);
        this.topic = topic;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void doWork() {
//        String userSchema = "{\"type\":\"record\"," +
//                "\"name\":\"myrecord\"," +
//                "\"fields\":[{\"name\":\"f1\",\"type\":\"string\"}]}";
//        Schema.Parser parser = new Schema.Parser();
//        Schema schema = parser.parse(userSchema);
//
//        DatumReader<GenericRecord> datumReader = new GenericDatumReader<>(schema);

        try {
            consumer.subscribe(Collections.singletonList(this.topic));
            ConsumerRecords<Integer, PbClasses.SearchRequest> records = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<Integer, PbClasses.SearchRequest> record : records) {
//                BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(record.value(), null);
                //                    GenericRecord genRecord = datumReader.read(null, decoder);
                System.out.println("Received message: (" + record.key() + ", " + record.value().toString() + ") at offset " + record.offset());
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
