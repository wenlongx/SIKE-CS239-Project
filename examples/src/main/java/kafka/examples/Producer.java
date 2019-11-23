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

import com.google.protobuf.InvalidProtocolBufferException;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import kafka.protobuf_serde.CustomProtobufSerializer;
import kafka.protobuf_serde.generated.PbClasses;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;

import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import scala.Int;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Producer extends Thread {
    private final KafkaProducer<Integer, PbClasses.SearchRequest> producer;
    private final String topic;
    private final Boolean isAsync;

    public Producer(String topic, Boolean isAsync, SerializerType serializerType) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.KAFKA_SERVER_URL + ":" + KafkaProperties.KAFKA_SERVER_PORT);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "DemoProducer");

        // The key will always be a int as the key is the partition name
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());

        switch (serializerType) {
            case PB:
                props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CustomProtobufSerializer.class.getName());
                break;
            case DEFAULT:
                props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
                break;
        }

//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, io.confluent.kafka.serializers.KafkaAvroSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());
        producer = new KafkaProducer<>(props);
        this.topic = topic;
        this.isAsync = isAsync;

        System.out.println("Created the producer");
    }

    public void run() {

//        String key = "key1";
//        String userSchema = "{\"type\":\"record\"," +
//                "\"name\":\"myrecord\"," +
//                "\"fields\":[{\"name\":\"f1\",\"type\":\"string\"}]}";
//        Schema.Parser parser = new Schema.Parser();
//        Schema schema = parser.parse(userSchema);
//        GenericRecord avroRecord = new GenericData.Record(schema);
//        avroRecord.put("f1", "value1");
//
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        BinaryEncoder encoder = null;
//        encoder = EncoderFactory.get().binaryEncoder(stream, encoder);
//
//        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(schema);
//        try {
//            datumWriter.write(avroRecord, encoder);
//            encoder.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        ProducerRecord<Object, Object> record = new ProducerRecord<>(this.topic, key, stream.toByteArray());
        System.out.println("Started to run the program");

        PbClasses.SearchRequest sr = PbClasses.SearchRequest.newBuilder().setPageNumber(12321).build();

        try {
            PbClasses.SearchRequest sr2 = PbClasses.SearchRequest.parseFrom(sr.toByteArray());
            System.out.println("The page number is " + sr2.getPageNumber());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        try {
            for (int i = 0; i < 100; i++) {
                producer.send(new ProducerRecord<>(this.topic, 5, sr));
                System.out.println("Sending data" + i);
            }
        } catch (SerializationException e) {
            // may need to do something with it
            System.out.println("Caught a serialization exception");
        } finally {
//            When you're finished producing records, you can flush the producer to ensure it has all been written to Kafka and
// then close the producer to free its resources.
            System.out.println("Closing the producer");
            producer.flush();
            producer.close();
        }

//        for (int i = 0; i < 1000; i++){
////            String messageStr = "Message_" + messageNo;
//            long startTime = System.currentTimeMillis();
//            if (isAsync) { // Send asynchronously
//                producer.send(new ProducerRecord<>(topic,
//                    messageNo,
//                        avroRecord));
////                , new DemoCallBack(startTime, messageNo, avroRecord));
//            } else { // Send synchronously
//                try {
//                    producer.send(new ProducerRecord<>(topic,
//                        messageNo,
//                            avroRecord)).get();
//                    System.out.println("Sent message: (" + messageNo);
//                } catch (InterruptedException | ExecutionException e) {
//                    e.printStackTrace();
//                }
//            }
//            ++messageNo;
//        }
    }
}

class DemoCallBack implements Callback {

    private final long startTime;
    private final int key;
    private final String message;

    public DemoCallBack(long startTime, int key, String message) {
        this.startTime = startTime;
        this.key = key;
        this.message = message;
    }

    /**
     * A callback method the user can implement to provide asynchronous handling of request completion. This method will
     * be called when the record sent to the server has been acknowledged. When exception is not null in the callback,
     * metadata will contain the special -1 value for all fields except for topicPartition, which will be valid.
     *
     * @param metadata  The metadata for the record that was sent (i.e. the partition and offset). Null if an error
     *                  occurred.
     * @param exception The exception thrown during processing of this record. Null if no error occurred.
     */
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (metadata != null) {
            System.out.println(
                    "message(" + key + ", " + message + ") sent to partition(" + metadata.partition() +
                            "), " +
                            "offset(" + metadata.offset() + ") in " + elapsedTime + " ms");
        } else {
            exception.printStackTrace();
        }
    }
}
