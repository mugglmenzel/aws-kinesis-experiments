#!/usr/bin/env bash

sudo apt-get update -y
sudo apt-get install -y git maven openjdk-7-jdk

git clone https://github.com/mugglmenzel/stockTradesConsumer.git
cd stockTradesConsumer
cat << EOT >> log4j.properties
# Root logger option
log4j.rootLogger=INFO, stdout

# Direct log messages to stdout
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n
EOT
mvn compile exec:java -Dexec.mainClass="com.amazonaws.services.kinesis.samples.stocktrades.processor.StockTradesProcessor" -Dexec.args="StockTradesConsumer KinesisLabStockTrade eu-west-1 TRIM_HORIZON" -Dlog4j.configuration=file:/stockTradesConsumer/log4j.properties > kpl.log