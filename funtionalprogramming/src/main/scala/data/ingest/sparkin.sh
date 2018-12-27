#!/bin/bash
# Read Password
echo -n Password: 
read -s password
echo
# Run Command
spark-shell -i SparkIngest.sc  --driver-class-path /tmp/ojdbc6.jar --conf spark.driver.args="map.json system $password"