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

import org.apache.kafka.common.serialization.Serializer;

import java.util.Scanner;

public class KafkaConsumerProducerDemo {
    public static void main(String[] args) {
        boolean isAsync = false;
        Producer producerThread = null;
        ConsumerTest consumerThread = null;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you wish to send the data in an async manner? (y/n)");
        String asyncResp = scanner.nextLine();
        if (asyncResp.toLowerCase().equals("y")) {
            isAsync = true;
        }

        System.out.println("Enter the mode you wish to run, Protobuf (p), Avro (a), default (d): ");
        String modeResp = scanner.nextLine();
        SerializerType currSerializer = null;

        switch (modeResp.toLowerCase()){
            case "p":
                currSerializer = SerializerType.PB;
                break;
            case "a":
                currSerializer = SerializerType.AVRO;
                break;
            case "d":
                currSerializer = SerializerType.DEFAULT;
                break;
            default:
                System.out.println("Invalid mdoe entered, exiting program now ...");
                return;
        }
        producerThread = new Producer(KafkaProperties.TOPIC, isAsync, currSerializer);
        consumerThread = new ConsumerTest(KafkaProperties.TOPIC, currSerializer);

        // Start the producer thread first and then the consumer thread
        producerThread.start();
        consumerThread.start();
    }
}
