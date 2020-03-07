package com.knoldus

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord, RecordMetadata}


object Producer {
  def main(args: Array[String]): Unit = {
    writeToKafka("user")
  }

  def writeToKafka(topic: String): Unit = {
    val properties: Properties = new Properties()
    properties.put("bootstrap.servers", "localhost:9092")
    properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    properties.put("value.serializer", "com.knoldus.UserSerializer")
    properties.put("acks", "all")
    val producer = new KafkaProducer[String, User](properties)
    try {
      def sendUser(users: List[User], count: Int) {
        users match {
          case user :: rest =>
            producer.send(new ProducerRecord[String, User](topic, count.toString, user), (metadata: RecordMetadata, exception: Exception) => {
                ReportLog.write(user, metadata, exception)
            })
            sendUser(rest, count + 1)
          case user :: Nil => producer.send(new ProducerRecord[String, User](topic, count.toString, user), (metadata: RecordMetadata, exception: Exception) => {
              ReportLog.write(user, metadata, exception)

          })
        }
        sendUser(UserData.getDeserialize, 0)
      }

      sendUser(UserData.getDeserialize, 0)
    }
    catch {
      case e: Exception => e.printStackTrace()
    } finally {
      producer.close()
    }
  }
}