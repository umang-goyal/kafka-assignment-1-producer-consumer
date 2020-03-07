name := "kafka-assignment-01-jsonstring-passing"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies += "net.liftweb" %% "lift-json" % "3.4.0"

libraryDependencies += "org.apache.kafka" %% "kafka" % "2.4.0"

libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.4.0"

libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.30"

libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.10.1"

// https://mvnrepository.com/artifact/org.codehaus.jackson/jackson-core-asl
libraryDependencies += "org.codehaus.jackson" % "jackson-core-asl" % "1.9.13"
