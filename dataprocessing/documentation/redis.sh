#!/usr/bin/env bash
REDIS_HOME='/usr/local/datapipeline/redis-3.2.6'

#Start server
$REDIS_HOME/src/redis-server

#Start client
$REDIS_HOME/src/redis-cli