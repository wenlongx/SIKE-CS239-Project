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

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import kafka.avro_serde.AvroSchemas;
import kafka.avro_serde.CustomAvroSerializer;
import kafka.capnproto_serde.CustomCapnProtoSerializer;
import kafka.capnproto_serde.generated.CapnProtoClasses;
import kafka.protobuf_serde.CustomProtobufSerializer;
import kafka.protobuf_serde.generated.PbClasses;
import kafka.thrift_serde.CustomThriftSerializer;
import kafka.thrift_serde.generated.ComplexMessage;
import kafka.thrift_serde.generated.NestedMessage;
import kafka.thrift_serde.generated.PrimitiveMessage;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.capnproto.MessageBuilder;
import org.capnproto.PrimitiveList;
import org.capnproto.StructList;

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

    @SuppressWarnings("unchecked")
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
            case CAPNPROTO1:
            case CAPNPROTO2:
            case CAPNPROTO3:
                producer = new KafkaProducer<>(props, new IntegerSerializer(), new CustomCapnProtoSerializer(serializerType, iterations));
                break;
            case AVRO_SCHEMAREG1:
            case AVRO_SCHEMAREG2:
            case AVRO_SCHEMAREG3:
                props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
                props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
                props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
                producer = new KafkaProducer<>(props);
                break;
            case THRIFT1:
            case THRIFT2:
            case THRIFT3:
                producer = new KafkaProducer<>(props, new IntegerSerializer(), new CustomThriftSerializer(serializerType, iterations));
                break;
        }

        this.topic = topic;
        this.serializerType = serializerType;
        this.iterations = iterations;

        System.out.println("Created the producer for " + this.serializerType.toString() + " with iterations: " + this.iterations);
    }

    @SuppressWarnings("unchecked")
    public void run() {
        System.out.println("Started to run the producer ...");
        List<Integer> integerArrayList = new ArrayList<>();

        int integerArraySize = 100;
        int stringIntegerMapSize = 100;

        for (int i = 0; i < integerArraySize; i++) {
            integerArrayList.add(i);
        }
        Map<String, Integer> stringIntegerMap = new HashMap<>();
        for (int i = 0; i < stringIntegerMapSize; i++) {
            stringIntegerMap.put("key" + i, i);
        }

        try {
            //10MB character query
            BufferedReader br = Files.newBufferedReader(Paths.get("./examples/src/main/java/kafka/examples/query.txt"), StandardCharsets.UTF_8);
            String longQuery = br.readLine();
//            String longQuery = "Hello There";
            switch (this.serializerType) {

                /////////////////////////////////////// PB CODE BELOW ////////////////////////////////////////

                case PB1:
                    for (int i = 0; i < this.iterations; i++) {
                        long startTime = System.nanoTime();

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
                        long startTime = System.nanoTime();

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
                        long startTime = System.nanoTime();

                        PbClasses.NestedMessage.Builder builder = PbClasses.NestedMessage.newBuilder();
                        builder.setTimestamp(startTime);
                        builder.setId(i);
                        builder.setPrimitiveMsg(PbClasses.PrimitiveMessage.newBuilder().setQuery("hello there").setPageNumber(12321).setResultPerPage(i).build());
                        PbClasses.NestedMessage nestedMessage = builder.build();

                        producer.send(new ProducerRecord<>(this.topic, 5, nestedMessage), new DemoCallBack(startTime, i));
                    }
                    break;

                /////////////////////////////////////// AVRO CODE BELOW ////////////////////////////////////////

                case AVRO_SCHEMAREG1:
                case AVRO1:
                    for (int i = 0; i < this.iterations; i++) {
                        long startTime = System.nanoTime();

                        GenericRecord record = new GenericData.Record(AvroSchemas.primitiveMessageSchema);
                        record.put("query", longQuery);
                        record.put("page_number", 12321);
                        record.put("timestamp", startTime);
                        record.put("result_per_page", i);

                        producer.send(new ProducerRecord<>(this.topic, 5, record), new DemoCallBack(startTime, i));
                    }
                    break;
                case AVRO_SCHEMAREG2:
                case AVRO2:
                    for (int i = 0; i < this.iterations; i++) {
                        long startTime = System.nanoTime();

                        GenericRecord record = new GenericData.Record(AvroSchemas.complexMessageSchema);
                        record.put("timestamp", startTime);
                        record.put("arr", integerArrayList);
                        record.put("storage", stringIntegerMap);

                        producer.send(new ProducerRecord<>(this.topic, 5, record), new DemoCallBack(startTime, i));
                    }
                    break;
                case AVRO_SCHEMAREG3:
                case AVRO3:
                    for (int i = 0; i < this.iterations; i++) {
                        long startTime = System.nanoTime();

                        GenericRecord primitiveMessage = new GenericData.Record(AvroSchemas.primitiveMessageSchema);
                        primitiveMessage.put("query", "hello there");
                        primitiveMessage.put("page_number", 12321);
                        primitiveMessage.put("result_per_page", i);

//                        String str_schema = "{\\r\\n  \\\"type\\\": \\\"record\\\",\\r\\n  \\\"name\\\": \\\"NestedMessage\\\",\\r\\n  \\\"fields\\\": [\\r\\n    {\\r\\n      \\\"name\\\": \\\"timestamp\\\",\\r\\n      \\\"type\\\": [\\r\\n        \\\"null\\\",\\r\\n        \\\"long\\\"\\r\\n      ],\\r\\n      \\\"default\\\": null\\r\\n    },\\r\\n    {\\r\\n      \\\"name\\\": \\\"id\\\",\\r\\n      \\\"type\\\": [\\r\\n        \\\"null\\\",\\r\\n        \\\"int\\\"\\r\\n      ],\\r\\n      \\\"default\\\": null\\r\\n    },\\r\\n    {\\r\\n      \\\"name\\\": \\\"primitiveMsg\\\",\\r\\n      \\\"type\\\": {\\r\\n        \\\"type\\\": \\\"record\\\",\\r\\n        \\\"name\\\": \\\"PrimitiveMessage\\\",\\r\\n        \\\"fields\\\": [\\r\\n          {\\r\\n            \\\"name\\\": \\\"timestamp\\\",\\r\\n            \\\"type\\\": [\\r\\n              \\\"null\\\",\\r\\n              \\\"long\\\"\\r\\n            ],\\r\\n            \\\"default\\\": null\\r\\n          },\\r\\n          {\\r\\n            \\\"name\\\": \\\"query\\\",\\r\\n            \\\"type\\\": [\\r\\n              \\\"null\\\",\\r\\n              \\\"string\\\"\\r\\n            ],\\r\\n            \\\"default\\\": null\\r\\n          },\\r\\n          {\\r\\n            \\\"name\\\": \\\"page_number\\\",\\r\\n            \\\"type\\\": [\\r\\n              \\\"null\\\",\\r\\n              \\\"int\\\"\\r\\n            ],\\r\\n            \\\"default\\\": null\\r\\n          },\\r\\n          {\\r\\n            \\\"name\\\": \\\"result_per_page\\\",\\r\\n            \\\"type\\\": [\\r\\n              \\\"null\\\",\\r\\n              \\\"int\\\"\\r\\n            ],\\r\\n            \\\"default\\\": null\\r\\n          }\\r\\n        ]\\r\\n      }\\r\\n    }\\r\\n  ]\\r\\n}";
//                        String str_schema = "{\"type\":\"record\",\"name\":\"NestedMessage\",\"fields\":[{\"name\":\"timestamp\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"id\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"primitiveMsg\",\"type\":{\"type\":\"record\",\"name\":\"PrimitiveMessage\",\"fields\":[{\"name\":\"timestamp\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"query\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"page_number\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"result_per_page\",\"type\":[\"null\",\"int\"],\"default\":null}]}}]}";
//                        Schema.Parser parser = new Schema.Parser();
//                        Schema schema = parser.parse(str_schema);
                        GenericRecord record = new GenericData.Record(AvroSchemas.nestedMessageSchema);
                        record.put("timestamp", startTime);
                        record.put("id", i);
                        record.put("primitiveMsg", primitiveMessage);

                        producer.send(new ProducerRecord<>(this.topic, 5, record), new DemoCallBack(startTime, i));
                    }
                    break;

                /////////////////////////////////////// CAPNPROTO CODE BELOW ////////////////////////////////////////

                case CAPNPROTO1:
                    for (int i = 0; i < this.iterations; i++) {
                        long startTime = System.nanoTime();
                        MessageBuilder message = new MessageBuilder();
                        CapnProtoClasses.PrimitiveMessage.Builder primitiveMessage = message.initRoot(CapnProtoClasses.PrimitiveMessage.factory);
                        primitiveMessage.setQuery(longQuery);
                        primitiveMessage.setTimestamp(startTime);
                        primitiveMessage.setPageNumber(12321);
                        primitiveMessage.setResultPerPage(i);

                        producer.send(new ProducerRecord<>(this.topic, 5, message), new DemoCallBack(startTime, i));
                    }
                    break;
                case CAPNPROTO2:
                    for (int i = 0; i < this.iterations; i++) {
                        long startTime = System.nanoTime();
                        MessageBuilder message = new MessageBuilder();
                        CapnProtoClasses.ComplexMessage.Builder complexMessage = message.initRoot(CapnProtoClasses.ComplexMessage.factory);
                        complexMessage.setTimestamp(startTime);

                        PrimitiveList.Int.Builder intArr = complexMessage.initArr(integerArraySize);
                        for (int j = 0; j < integerArraySize; j++) {
                            intArr.set(j, j);
                        }

                        StructList.Builder<CapnProtoClasses.ComplexMessage.Entry.Builder> localStringIntegerMap = complexMessage.initStorage(stringIntegerMapSize);
                        for (int j = 0; j < stringIntegerMapSize; j++) {
                            localStringIntegerMap.get(j).setKey("key" + j);
                            localStringIntegerMap.get(j).setValue(j);
                        }

                        producer.send(new ProducerRecord<>(this.topic, 5, message), new DemoCallBack(startTime, i));
                    }
                    break;
                case CAPNPROTO3:
                    for (int i = 0; i < this.iterations; i++) {
                        long startTime = System.nanoTime();
                        MessageBuilder message = new MessageBuilder();
                        CapnProtoClasses.NestedMessage.Builder nestedMessage = message.initRoot(CapnProtoClasses.NestedMessage.factory);
                        nestedMessage.setTimestamp(startTime);
                        nestedMessage.setId(i);

                        CapnProtoClasses.PrimitiveMessage.Builder primitiveMessage = nestedMessage.initPrimitiveMessage();
                        primitiveMessage.setQuery(longQuery);
                        primitiveMessage.setTimestamp(startTime);
                        primitiveMessage.setPageNumber(12321);
                        primitiveMessage.setResultPerPage(i);

                        producer.send(new ProducerRecord<>(this.topic, 5, message), new DemoCallBack(startTime, i));
                    }
                    break;

                /////////////////////////////////////// THRIFT CODE BELOW ////////////////////////////////////////

                case THRIFT1:
                    for (int i = 0; i < this.iterations; i++) {
                        long startTime = System.nanoTime();
                        PrimitiveMessage primitiveMessage = new PrimitiveMessage();
                        primitiveMessage.setQuery(longQuery);
                        primitiveMessage.setTimestamp(startTime);
                        primitiveMessage.setPageNumber(12321);
                        primitiveMessage.setResultPerPage(i);

                        producer.send(new ProducerRecord<>(this.topic, 5, primitiveMessage), new DemoCallBack(startTime, i));
                    }
                    break;
                case THRIFT2:
                    for (int i = 0; i < this.iterations; i++) {
                        long startTime = System.nanoTime();
                        ComplexMessage complexMessage = new ComplexMessage();
                        complexMessage.setTimestamp(startTime);
                        complexMessage.setArr(integerArrayList);
                        complexMessage.setStorage(stringIntegerMap);

                        producer.send(new ProducerRecord<>(this.topic, 5, complexMessage), new DemoCallBack(startTime, i));
                    }
                    break;
                case THRIFT3:
                    for (int i = 0; i < this.iterations; i++) {
                        long startTime = System.nanoTime();

                        PrimitiveMessage primitiveMessage = new PrimitiveMessage();
                        primitiveMessage.setQuery(longQuery);
                        primitiveMessage.setTimestamp(startTime);
                        primitiveMessage.setPageNumber(12321);
                        primitiveMessage.setResultPerPage(i);

                        NestedMessage nestedMessage = new NestedMessage();
                        nestedMessage.setTimestamp(startTime);
                        nestedMessage.setId(i);
                        nestedMessage.setPrimitiveMsg(primitiveMessage);

                        producer.send(new ProducerRecord<>(this.topic, 5, nestedMessage), new DemoCallBack(startTime, i));
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
        long elapsedTime = System.nanoTime() - startTime;
        if (metadata != null) {
            System.out.println("Message " + messageNumber + " was sent and it took " + elapsedTime + " ns.");
        } else {
            exception.printStackTrace();
        }
    }
}