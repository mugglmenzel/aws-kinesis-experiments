#!/usr/bin/env bash

sudo apt-get update -y
sudo apt-get install -y git maven openjdk-7-jdk

git clone https://github.com/fabiantan/stock-trade-generator.git
cd stock-trade-generator
mvn assembly:assembly
nohup java -cp target/StockTradeGenerator-1.0.0-complete.jar -Dstream-name=KinesisLabStockTrade -Dbackpressure-size=50000 -Dbackpressure-delay=500 com.amazonaws.services.kinesis.application.producer.Generator &

