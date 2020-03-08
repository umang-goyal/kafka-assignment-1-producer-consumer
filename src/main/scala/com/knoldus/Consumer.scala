package com.knoldus

import java.time.Duration
import java.util
import java.util.Properties

import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.slf4j.{Logger, LoggerFactory}
//import scala.collection.JavaConverters._
import scala.jdk.CollectionConverters._

object Consumer {
  val logger: Logger = LoggerFactory.getLogger(Consumer.getClass)

  def main(args: Array[String]): Unit = {
    consumeFromKafka("user")
  }

  def consumeFromKafka(topic: String): Unit = {

    val properties = new Properties()
    properties.put("bootstrap.servers", "localhost:9092")
    properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    properties.put("value.deserializer", "com.knoldus.UserDeserializer")
    properties.put("auto.offset.reset", "latest")
    properties.put("group.id", "consumer-group")

    val consumer: KafkaConsumer[String, User] = new KafkaConsumer[String, User](properties)

    consumer.subscribe(util.Arrays.asList(topic))
    implicit val formats: DefaultFormats.type = DefaultFormats
    val timeout= 1000
    val record = consumer.poll(Duration.ofMillis(timeout)).asScala
    val userDataInJson = for (data <- record.iterator) yield write(data.value())
    for (user <- userDataInJson) {
      logger.info(user)
    }
  }
}