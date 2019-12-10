Wenlong Xiong (204407085)

Sahil Gandhi

Kaushik Mahorker

# Our Goal

We are testing the performance of different serialization methods as they apply to a distributed setting. To run these tests we chose Kafka because it has an extensible serialization interface (SerDe).

# Starting Kafka and Zookeeper

Before running any example, open up 2 terminals (or tmux) and run the following commands to start the Kafka server and Zookeeper

``` shell script
$ cd confluent-kafka
$ bin/zookeeper-server-start ./etc/kafka/zookeeper.properties
$ bin/kafka-server-start ./etc/kafka/server.properties
$ bin/schema-registry-start ./etc/schema-registry/schema-registry.properties
```

# TODO List
1. Add dependencies to gradle as needed
2. Modularize project for different serializers
3. Implement serde for streams (if we want to)
4. Look into schema registry?? <-- I feel like this is sorta unnecessary for pure kafka 
5. Profiling script
    - Should this be in Java or some kind of python wrapper??