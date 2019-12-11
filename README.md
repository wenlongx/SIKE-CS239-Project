Wenlong Xiong (204407085)

Sahil Gandhi

Kaushik Mahorker

# Our Goal

We are testing the performance of different serialization methods as they apply to a distributed setting. To run these tests we chose Kafka because it has an extensible serialization interface (SerDe).

# Starting Kafka, Zookeeper and Schema Registry

Before running any example, open up 3 terminals (or tmux) and run the following commands to start the Kafka server, Zookeeper, and Schema Registry

``` shell script
$ cd confluent-kafka
$ bin/zookeeper-server-start ./etc/kafka/zookeeper.properties
$ bin/kafka-server-start ./etc/kafka/server.properties
$ bin/schema-registry-start ./etc/schema-registry/schema-registry.properties

$ curl -X PUT -H "Content-Type: application/json" \
     --data '{"compatibility": "NONE"}' \
     http://localhost:8081/config/{topic_name}-value
```

To start just Kafka server and Zookeeper, run the following
``` shell script
$ bin/zookeeper-server-start.sh config/zookeeper.properties
$ bin/kafka-server-start.sh config/server.properties
```