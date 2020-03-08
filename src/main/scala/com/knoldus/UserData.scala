package com.knoldus

import net.liftweb.json.DefaultFormats

import scala.io.{BufferedSource, Source}

object UserData {
  val bufferedSource: BufferedSource = Source.fromFile("./src/main/resources/json.txt")
  val jsonData: String = bufferedSource.mkString
  implicit val formats: DefaultFormats.type = DefaultFormats

  def getDeserialize: List[User] = {
    val parsedJsonData = net.liftweb.json.parse(jsonData)
    parsedJsonData.children map { userData =>
      userData.extract[User]
    }
  }
  bufferedSource.close()
}