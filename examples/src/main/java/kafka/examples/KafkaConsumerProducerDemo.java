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

import java.util.Scanner;

public class KafkaConsumerProducerDemo {
    private final int [] ITERATIONS = {10};

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the mode you wish to run, Protobuf (p), Avro (a): ");
        String modeResp = scanner.nextLine();
        KafkaConsumerProducerDemo kafkaConsumerProducerDemo = new KafkaConsumerProducerDemo();
        for (int iterations : kafkaConsumerProducerDemo.ITERATIONS){
            switch (modeResp.toLowerCase()) {
                case "p":
                    kafkaConsumerProducerDemo.run(SerializerType.PB1, iterations);
                    kafkaConsumerProducerDemo.run(SerializerType.PB2, iterations);
                    kafkaConsumerProducerDemo.run(SerializerType.PB3, iterations);
                    break;
                case "a":
                    kafkaConsumerProducerDemo.run(SerializerType.AVRO1, iterations);
                    kafkaConsumerProducerDemo.run(SerializerType.AVRO2, iterations);
                    kafkaConsumerProducerDemo.run(SerializerType.AVRO3, iterations);
                    break;
                default:
                    System.out.println("Invalid mode entered, exiting program now ...");
            }
        }
    }

    private void run(SerializerType serializerType, int iterations) throws  InterruptedException{

        ProducerThread producerThread = new ProducerThread(KafkaProperties.TOPIC, serializerType, iterations);
        ConsumerThread consumerThread = new ConsumerThread(KafkaProperties.TOPIC, serializerType, iterations);

        // Start the producer thread first and then the consumer thread
        producerThread.start();
        consumerThread.start();

        consumerThread.join();
    }
}
