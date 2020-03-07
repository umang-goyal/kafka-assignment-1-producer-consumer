package com.knoldus

case class User(id: Int, name: String, age: Int, address: String){
  def data: String = s"userId: $id name: $name age: $age address: $address"
}
