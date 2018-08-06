#Apache Kafka is a distributed, high-throughput message queuing system designed for making streaming data available to multiple data consumer. Kafka makes the streaming data durable by persisting incoming message on the disk using log data structure.
#This allow various downstream consumers to read the stream at different position and different speeds and also read messages from the past is replaying history.


#Most imp abstraction of Kafka is "TOPIC"
#A topic is a handle to a logical stream of data.
#"Partitions" are subsets of the data served by the topic that resides in different physical nodes.
#Service that put data in to topic are called "PRODUCER"
#The opposite of a producer i.e a service that reads data from a topic is called a "CONSUMER"
#The individual partitions of a topic are managed by a "Kafka broker", A service that installed on the node that contains the partition and allows consumers and producers to access the data of a topic.
#When a partition is replicated many broker might be managing the same partition. Then one of these broker is designated as the "LEADER" and the rest are "FOLLOWERS". Kafka is assigned each message within a partition a unique ID the so-called "message offset", which represents a unique, increasing logical timestamp within the partition. This offset allows consumers to request messages from a certain offset onwards, essentially consuming data from a given post logical time.

#partition 0 [0 1 2 3 4 .....]

#To enable this model for multiple consumers, there is a concept of "Consumer Group"


#start server

bin/kafka-server-start.sh config/server.properties

#stop server

bin/kafka-server-stop.sh config/server.properties

#create a topic

bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic Hello-Kafka

#create a topic for multiple broker

bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 3 -partitions 1 --topic Multibrokerapplication

#list all topic

bin/kafka-topics.sh --list --zookeeper localhost:2181

#console producer

bin/kafka-console-producer.sh --broker-list localhost:9092 --topic Hello-Kafka

#console consumer

bin/kafka-console-consumer.sh --zookeeper localhost:2181 —topic Hello-Kafka --from-beginning

#describe the topic

bin/kafka-topics.sh --describe --zookeeper localhost:2181 --topic Multibrokerapplication

#console producer with broker list

bin/kafka-console-producer.sh --broker-list localhost:9092 --topic Multibrokerapplication

#console consumer

bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic Multibrokerapplication --from-beginning
