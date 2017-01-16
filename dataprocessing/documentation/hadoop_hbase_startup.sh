#!/usr/bin/env bash


# Format Hadoop namenode
# $HADOOP_HOME/bin/hdfs namenode -format

#Start namenode and datanode daemon
$HADOOP_HOME/sbin/start-dfs.sh

#Start resource manager and nodemanager

$HADOOP_HOME/sbin/start-yarn.sh

#Stop yarn daemon
#$HADOOP_HOME/sbin/stop-dfs.sh
#$HADOOP_HOME/sbin/stop-yarn.sh

#Start HBase, before start HBase, zookeeper need to be started.
$HBASE_HOME/bin/start-hbase.sh

#$HBASE_HOME/bin/stop-hbase.sh