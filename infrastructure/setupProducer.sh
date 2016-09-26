#!/usr/bin/env bash

sudo apt-get update -y
sudo apt-get install -y git

sudo add-apt-repository -y ppa:webupd8team/java
sudo apt-get update -y
echo "debconf shared/accepted-oracle-license-v1-1 select true" | sudo debconf-set-selections
echo "debconf shared/accepted-oracle-license-v1-1 seen true" | sudo debconf-set-selections
sudo apt-get install -y oracle-java8-installer


echo "deb https://dl.bintray.com/sbt/debian /" | sudo tee -a /etc/apt/sources.list.d/sbt.list
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 642AC823
sudo apt-get update -y
sudo apt-get install -y sbt

git clone https://github.com/mugglmenzel/aws-kinesis-experiments.git
bash aws-kinesis-experiments/kinesis-stream-producer/produce45.sh > /dev/null