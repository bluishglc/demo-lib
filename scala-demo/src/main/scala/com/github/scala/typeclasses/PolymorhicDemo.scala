package com.github.scala.typeclasses

import scala.math.{Pi, sqrt}

object PolymorphicDemo {

    trait Shape {
        def area:Double
    }

    case class Circle(radius:Double) extends Shape {
        def area:Double = 2*Pi*radius
    }

    case class EQLTriangle(side:Double) extends Shape {
        def area:Double = (sqrt(3)/4)*side*side
    }

    val shapes = Vector(Circle(2.2), EQLTriangle(3.9), Circle(4.5))

    def a(s:Shape) = f"$s area: ${s.area}%.2f"

    val result = for(s <- shapes) yield a(s)

    def main(args: Array[String]) {
        println(result)
    }

}