#!/usr/bin/env bash
FLUME_HOME='/usr/local/datapipeline/apache-flume-1.7.0-bin'

$FLUME_HOME/bin/flume-ng agent --conf conf --conf-file $FLUME_HOME/conf/example.conf --name agent_serverlog_1 -Dflume.root.logger=INFO,console