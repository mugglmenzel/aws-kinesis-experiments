# kinesis-stream-producer

An implementation of a Kinesis prosumer client in Scala. The client produces data for each shard of a Kinesis stream. Optionally the stream data can be read (consumer) in parallel to the producer.

## Usage

The program can be run by executing the following command:

```bash
sbt "run -n [stream name]"
```

Alternatively, a portable, standalone JAR file can be produced and run with the following commands:

```bash
sbt assembly
java -jar target/scala-2.11/kinesis-stream-producer-assembly-1.0.jar -n [stream name]
```
Without providing any arguments the program will show its usage information including all supported parameters and options.


Moreover, three helper shell scripts are provided that execute prosumers and producers assuming 2 existing Kinesis streams, namely "KinesisLab" with 3 shards and "KinesisLab45" with 45 shards. Note that the program can work with any number of shards and detects the stream details autonomously.
The 3 scripts are the following:

```bash
#prosume "KinesisLab" stream
bash run3.sh 
#prosume "KinesisLab45" stream
bash run45.sh
#produce "KinesisLab45" stream
bash produce45.sh
```