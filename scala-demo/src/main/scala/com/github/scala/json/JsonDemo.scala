package com.github.scala.json

import org.json4s._
import org.json4s.native.JsonMethods._
import scala.io._

object JsonDemo {

    def main(args: Array[String]) {
        case class Person(name: String, age: Int)
        val json = """{"name":"joe","age":15}"""
        implicit val formats = DefaultFormats
        val p = parse(json).extract[Person]
        println(p.name)
    }

}
