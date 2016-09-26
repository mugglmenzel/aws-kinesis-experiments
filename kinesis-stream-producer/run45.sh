#!/usr/bin/env bash

cd "$(dirname "$0")"

if [ ! -e target/scala-2.11/kinesis-stream-producer-assembly-1.0.jar ]
    then
        echo "Building ktdp program with SBT..."
        sbt assembly || echo "SBT is required!"
fi

echo "Running ktdp with a 45-sharded Kinesis stream..."
java -jar target/scala-2.11/kinesis-stream-producer-assembly-1.0.jar -n KinesisLab45