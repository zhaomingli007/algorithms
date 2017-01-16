#!/usr/bin/env bash

KAFKA_HOME='/usr/local/datapipeline/kafka_2.11-0.10.1.1'
TOPIC='my-replicated-topic'

#Start zookeeper
$KAFKA_HOME/bin/zookeeper-server-start.sh $KAFKA_HOME/config/zookeeper.properties &

#Start kafka broker 9092,9093,9094
$KAFKA_HOME/bin/kafka-server-start.sh $KAFKA_HOME/config/server.properties &
$KAFKA_HOME/bin/kafka-server-start.sh $KAFKA_HOME/config/server-1.properties &
$KAFKA_HOME/bin/kafka-server-start.sh $KAFKA_HOME/config/server-2.properties &

#Create a topic
$KAFKA_HOME/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic $TOPIC

#Describe
#$KAFKA_HOME/bin/kafka-topics.sh --describe --zookeeper localhost:2181 --topic $TOPIC

#Consume
#$KAFKA_HOME/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic my-replicated-topic

#Purge kafka messages of a topic
#$KAFKA_HOME/bin/kafka-topics.sh --delete --zookeeper localhost:2181  --topic userevent1