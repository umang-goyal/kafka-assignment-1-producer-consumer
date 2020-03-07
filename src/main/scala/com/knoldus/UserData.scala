package com.knoldus

import net.liftweb.json.DefaultFormats

import scala.io.Source

object UserData {

  val jsonData: String = Source.fromFile("./src/main/resources/json.txt").mkString
  implicit val formats: DefaultFormats.type = DefaultFormats

  def getDeserialize: List[User] = {
    val parsedJsonData = net.liftweb.json.parse(jsonData)
    parsedJsonData.children map { userData =>
      userData.extract[User]
    }
  }
}