# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

import time
from ducktape.tests.test import Test
from ducktape.utils.util import wait_until
from kafkatest.services.kafka import KafkaService
from kafkatest.services.streams import StreamsNamedRepartitionTopicService
from kafkatest.services.verifiable_producer import VerifiableProducer
from kafkatest.services.zookeeper import ZookeeperService


class StreamsNamedRepartitionTopicTest(Test):
    """
    Tests using a named repartition topic by starting
    application then doing a rolling upgrade with added
    operations and the application still runs
    """

    input_topic = 'inputTopic'
    aggregation_topic = 'aggregationTopic'
    pattern = 'AGGREGATED'

    def __init__(self, test_context):
        super(StreamsNamedRepartitionTopicTest, self).__init__(test_context)
        self.topics = {
            self.input_topic: {'partitions': 6},
            self.aggregation_topic: {'partitions': 6}
        }

        self.zookeeper = ZookeeperService(self.test_context, num_nodes=1)
        self.kafka = KafkaService(self.test_context, num_nodes=3,
                                  zk=self.zookeeper, topics=self.topics)

        self.producer = VerifiableProducer(self.test_context,
                                           1,
                                           self.kafka,
                                           self.input_topic,
                                           throughput=1000,
                                           acks=1)

    def test_upgrade_topology_with_named_repartition_topic(self):
        self.zookeeper.start()
        self.kafka.start()

        processor1 = StreamsNamedRepartitionTopicService(self.test_context, self.kafka)
        processor2 = StreamsNamedRepartitionTopicService(self.test_context, self.kafka)
        processor3 = StreamsNamedRepartitionTopicService(self.test_context, self.kafka)

        processors = [processor1, processor2, processor3]

        self.producer.start()

        for processor in processors:
            processor.CLEAN_NODE_ENABLED = False
            self.set_topics(processor)
            self.verify_running(processor, 'REBALANCING -> RUNNING')

        self.verify_processing(processors)

        # do rolling upgrade
        for processor in processors:
            self.verify_stopped(processor)
            #  will tell app to add operations before repartition topic
            processor.ADD_ADDITIONAL_OPS = 'true'
            self.verify_running(processor, 'UPDATED Topology')

        self.verify_processing(processors)

        self.stop_processors(processors)

        self.producer.stop()
        self.kafka.stop()
        self.zookeeper.stop()

    @staticmethod
    def verify_running(processor, message):
        node = processor.node
        with node.account.monitor_log(processor.STDOUT_FILE) as monitor:
            processor.start()
            monitor.wait_until(message,
                               timeout_sec=60,
                               err_msg="Never saw '%s' message " % message + str(processor.node.account))

    @staticmethod
    def verify_stopped(processor):
        node = processor.node
        with node.account.monitor_log(processor.STDOUT_FILE) as monitor:
            processor.stop()
            monitor.wait_until('NAMED_REPARTITION_TEST Streams Stopped',
                               timeout_sec=60,
                               err_msg="'NAMED_REPARTITION_TEST Streams Stopped' message" + str(processor.node.account))

    def verify_processing(self, processors):
        for processor in processors:
            with processor.node.account.monitor_log(processor.STDOUT_FILE) as monitor:
                monitor.wait_until(self.pattern,
                                   timeout_sec=60,
                                   err_msg="Never saw processing of %s " % self.pattern + str(processor.node.account))

    def stop_processors(self, processors):
        for processor in processors:
            self.verify_stopped(processor)

    def set_topics(self, processor):
        processor.INPUT_TOPIC = self.input_topic
        processor.AGGREGATION_TOPIC = self.aggregation_topic
