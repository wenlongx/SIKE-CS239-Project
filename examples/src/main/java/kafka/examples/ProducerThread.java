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

import kafka.avro_serde.AvroSchemas;
import kafka.avro_serde.CustomAvroSerializer;
import kafka.protobuf_serde.CustomProtobufSerializer;
import kafka.protobuf_serde.generated.PbClasses;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.IntegerSerializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ProducerThread extends Thread {
    private Producer producer;
    private final String topic;
    private final SerializerType serializerType;
    private final int iterations;

    public ProducerThread(String topic, SerializerType serializerType, int iterations) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.KAFKA_SERVER_URL + ":" + KafkaProperties.KAFKA_SERVER_PORT);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "DemoProducer");

        // The key is the Partition Name, and for our experiments will be an integer.
        switch (serializerType) {
            case AVRO1:
                producer = new KafkaProducer<>(props, new IntegerSerializer(), new CustomAvroSerializer(AvroSchemas.primitiveMessageSchema, serializerType, iterations));
                break;
            case AVRO2:
                producer = new KafkaProducer<>(props, new IntegerSerializer(), new CustomAvroSerializer(AvroSchemas.complexMessageSchema, serializerType, iterations));
                break;
            case AVRO3:
                producer = new KafkaProducer<>(props, new IntegerSerializer(), new CustomAvroSerializer(AvroSchemas.nestedMessageSchema, serializerType, iterations));
                break;
            case PB1:
            case PB2:
            case PB3:
                producer = new KafkaProducer<>(props, new IntegerSerializer(), new CustomProtobufSerializer<>(serializerType, iterations));
                break;
        }

        this.topic = topic;
        this.serializerType = serializerType;
        this.iterations = iterations;

        System.out.println("Created the producer");
    }

    @SuppressWarnings("unchecked")
    public void run() {
        System.out.println("Started to run the producer ...");
        List<Integer> integerArrayList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            integerArrayList.add(i);
        }
        Map<String, Integer> stringIntegerMap = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            stringIntegerMap.put("key" + i, i);
        }

        try {
            //10MB character query
            BufferedReader br = Files.newBufferedReader(Paths.get("./examples/src/main/java/kafka/examples/query.txt"), StandardCharsets.UTF_8);
//            String longQuery = br.readLine();
            String longQuery = "Hello There";
            switch (this.serializerType) {
                case PB1:
                    for (int i = 0; i < this.iterations; i++) {
                        long startTime = System.currentTimeMillis();

                        PbClasses.PrimitiveMessage.Builder builder = PbClasses.PrimitiveMessage.newBuilder();
                        builder.setQuery(longQuery);
                        builder.setPageNumber(12321);
                        builder.setTimestamp(startTime);
                        builder.setResultPerPage(i);
                        PbClasses.PrimitiveMessage primitiveMessage = builder.build();

                        producer.send(new ProducerRecord<>(this.topic, 5, primitiveMessage), new DemoCallBack(startTime, i));
                    }
                    break;
                case PB2:
                    for (int i = 0; i < this.iterations; i++) {
                        long startTime = System.currentTimeMillis();

                        PbClasses.ComplexMessage.Builder builder = PbClasses.ComplexMessage.newBuilder();
                        builder.setTimestamp(startTime);
                        builder.addAllArr(integerArrayList);
                        builder.putAllStorage(stringIntegerMap);
                        PbClasses.ComplexMessage complexMessage = builder.build();

                        producer.send(new ProducerRecord<>(this.topic, 5, complexMessage), new DemoCallBack(startTime, i));
                    }
                    break;
                case PB3:
                    for (int i = 0; i < this.iterations; i++) {
                        long startTime = System.currentTimeMillis();

                        PbClasses.NestedMessage.Builder builder = PbClasses.NestedMessage.newBuilder();
                        builder.setTimestamp(startTime);
                        builder.setId(i);
                        builder.setPrimitiveMsg(PbClasses.PrimitiveMessage.newBuilder().setQuery("hello there").setPageNumber(12321).setResultPerPage(i).build());
                        PbClasses.NestedMessage nestedMessage = builder.build();

                        producer.send(new ProducerRecord<>(this.topic, 5, nestedMessage), new DemoCallBack(startTime, i));
                    }
                    break;

                /////////////////////////////////////// AVRO CODE BELOW ////////////////////////////////////////

                case AVRO1:
                    for (int i = 0; i < this.iterations; i++) {
                        long startTime = System.currentTimeMillis();

                        GenericRecord record = new GenericData.Record(AvroSchemas.primitiveMessageSchema);
                        record.put("query", longQuery);
                        record.put("page_number", 12321);
                        record.put("timestamp", startTime);
                        record.put("result_per_page", i);

                        producer.send(new ProducerRecord<>(this.topic, 5, record), new DemoCallBack(startTime, i));
                    }
                    break;
                case AVRO2:
                    for (int i = 0; i < this.iterations; i++) {
                        long startTime = System.currentTimeMillis();

                        GenericRecord record = new GenericData.Record(AvroSchemas.complexMessageSchema);
                        record.put("timestamp", startTime);
                        record.put("arr", integerArrayList);
                        record.put("storage", stringIntegerMap);

                        producer.send(new ProducerRecord<>(this.topic, 5, record), new DemoCallBack(startTime, i));
                    }
                    break;
                case AVRO3:
                    for (int i = 0; i < this.iterations; i++) {
                        long startTime = System.currentTimeMillis();

                        GenericRecord primitiveMessage = new GenericData.Record(AvroSchemas.primitiveMessageSchema);
                        primitiveMessage.put("query", "hello there");
                        primitiveMessage.put("page_number", 12321);
                        primitiveMessage.put("result_per_page", i);

                        GenericRecord record = new GenericData.Record(AvroSchemas.nestedMessageSchema);
                        record.put("timestamp", startTime);
                        record.put("id", i);
                        record.put("primitiveMsg", primitiveMessage);

                        producer.send(new ProducerRecord<>(this.topic, 5, record), new DemoCallBack(startTime, i));
                    }
                    break;
            }

        } catch (SerializationException | IOException e) {
            System.out.println(e);
        } finally {
//            When you're finished producing records, you can flush the producer to ensure it has all been written to
//            Kafka and then close the producer to free its resources.
            System.out.println("Closing the producer ...");
            producer.flush();
            producer.close();
        }
    }
}

class DemoCallBack implements Callback {

    private final long startTime;
    private final int messageNumber;

    public DemoCallBack(long startTime, int messageNumber) {
        this.startTime = startTime;
        this.messageNumber = messageNumber;
    }

    /**
     * A callback method the user can implement to provide asynchronous handling of request completion. This method will
     * be called when the record sent to the server has been acknowledged. When exception is not null in the callback,
     * metadata will contain the special -1 value for all fields except for topicPartition, which will be valid.
     * The acknowledgment is from the broker (NOT the consumer).
     *
     * @param metadata  The metadata for the record that was sent (i.e. the partition and offset). Null if an error
     *                  occurred.
     * @param exception The exception thrown during processing of this record. Null if no error occurred.
     */
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (metadata != null) {
            System.out.println("Message " + messageNumber + " was sent and it took " + elapsedTime + " ms.");
        } else {
            exception.printStackTrace();
        }
    }
}