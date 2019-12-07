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

import java.io.*;
import java.util.Scanner;

public class KafkaConsumerProducerDemo {
    private final int[] ITERATIONS = {10};

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the mode you wish to run, Protobuf (p), Avro (a), Avro_SchemaReg (s), Cap'nProto (c), Thrift (t): ");
        String modeResp = scanner.nextLine().toLowerCase();
        KafkaConsumerProducerDemo kafkaConsumerProducerDemo = new KafkaConsumerProducerDemo();
        for (int iterations : kafkaConsumerProducerDemo.ITERATIONS) {
            switch (modeResp) {
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
                case "c":
                    kafkaConsumerProducerDemo.run(SerializerType.CAPNPROTO1, iterations);
                    kafkaConsumerProducerDemo.run(SerializerType.CAPNPROTO2, iterations);
                    kafkaConsumerProducerDemo.run(SerializerType.CAPNPROTO3, iterations);
                    break;
                case "s":
                    kafkaConsumerProducerDemo.run(SerializerType.AVRO_SCHEMAREG1, iterations);
                    kafkaConsumerProducerDemo.run(SerializerType.AVRO_SCHEMAREG2, iterations);
                    kafkaConsumerProducerDemo.run(SerializerType.AVRO_SCHEMAREG3, iterations);
                    break;
                case "t":
                    kafkaConsumerProducerDemo.run(SerializerType.THRIFT1, iterations);
                    kafkaConsumerProducerDemo.run(SerializerType.THRIFT2, iterations);
                    kafkaConsumerProducerDemo.run(SerializerType.THRIFT3, iterations);
                    break;
                default:
                    System.out.println("Invalid mode entered, exiting program now ...");
            }
        }

        System.out.println("Converting text files now ...");

        for (int iterations : kafkaConsumerProducerDemo.ITERATIONS) {
            for (SerializerType serializerType : SerializerType.values()) {
                if (
                        (modeResp.equals("p") && (serializerType == SerializerType.PB1 || serializerType == SerializerType.PB2 || serializerType == SerializerType.PB3)) ||
                                (modeResp.equals("c") && (serializerType == SerializerType.CAPNPROTO1 || serializerType == SerializerType.CAPNPROTO2 || serializerType == SerializerType.CAPNPROTO3)) ||
                                (modeResp.equals("t") && (serializerType == SerializerType.THRIFT1 || serializerType == SerializerType.THRIFT2 || serializerType == SerializerType.THRIFT3)) ||
                                (modeResp.equals("a") && (serializerType == SerializerType.AVRO1 || serializerType == SerializerType.AVRO2 || serializerType == SerializerType.AVRO3))) {
                    try {
                        PrintWriter pw = new PrintWriter(serializerType.toString() + "_" + iterations + "_serdes.csv");
                        String serFileName = serializerType.toString() + "_" + iterations + "_ser.txt";
                        String deserFileName = serializerType.toString() + "_" + iterations + "_des.txt";
                        File serFile = new File(serFileName);
                        File deserFile = new File(deserFileName);

                        BufferedReader ser = new BufferedReader((new FileReader(serFile)));
                        BufferedReader des = new BufferedReader((new FileReader(deserFile)));

                        String serLine = ser.readLine();
                        String desLine = des.readLine();
                        while (serLine != null && desLine != null) {
                            pw.println(serLine + "," + desLine);
                            serLine = ser.readLine();
                            desLine = des.readLine();
                        }

                        pw.flush();
                        ser.close();
                        des.close();
                        pw.close();

                        boolean ignored = serFile.delete();
                        ignored = deserFile.delete();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void run(SerializerType serializerType, int iterations) throws InterruptedException {

        ProducerThread producerThread = new ProducerThread(KafkaProperties.TOPIC, serializerType, iterations);
        ConsumerThread consumerThread = new ConsumerThread(KafkaProperties.TOPIC, serializerType, iterations);

        // Start the producer thread first and then the consumer thread
        producerThread.start();
        consumerThread.start();

        consumerThread.join();
    }
}
