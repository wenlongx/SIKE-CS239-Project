Wenlong Xiong (204407085)

Sahil Gandhi

Kaushik Mahorker

# Starting Kafka and Zookeeper

Before running any example, open up 2 terminals (or tmux) and run the following commands to start the Kafka server and Zookeeper

``` shell script
$ bin/zookeeper-server-start.sh config/zookeeper.properties
$ bin/kafka-server-start.sh config/server.properties
```

# TODO List
1. Add dependencies to gradle as needed
2. Modularize project for different serializers
3. Implement serde for streams (if we want to)
4. Look into schema registry?? <-- I feel like this is sorta unnecessary for pure kafka 
5. Profiling script
    - Should this be in Java or some kind of python wrapper??