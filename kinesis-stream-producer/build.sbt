name := "kinesis-stream-producer"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "com.amazonaws" % "aws-java-sdk" % "1.11.37"
libraryDependencies += "com.github.scopt" %% "scopt" % "3.5.0"
libraryDependencies += "org.slf4j" % "slf4j-log4j12" % "1.7.21"

mainClass in Compile := Some("DataProducerApp")