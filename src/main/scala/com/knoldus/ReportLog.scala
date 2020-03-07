package com.knoldus

import java.io.{BufferedWriter, File, FileWriter}

import org.apache.kafka.clients.producer.RecordMetadata

object ReportLog {
  def write(user: User, metadata: RecordMetadata, exception: Exception): Unit = {
    if(exception!=null) {
      val writer = new BufferedWriter(new FileWriter(new File("./src/main/resources/logs.txt"), true))
      writer.write(s"report sent(${user.data})   " +
        s"{metadata: topic: ${metadata.topic} partition: ${metadata.partition} offset: ${metadata.offset} timestamp: ${metadata.timestamp}} \n")
      writer.close()
    } else {
      val writer = new BufferedWriter(new FileWriter(new File("./src/main/resources/logs.txt"), true))
      writer.write(s"ERROR: $exception, ${user.data} " +
        s"{metadata: topic: ${metadata.topic} partition: ${metadata.partition} offset: ${metadata.offset} timestamp: ${metadata.timestamp}} \n")
      writer.close()
    }
  }

}
