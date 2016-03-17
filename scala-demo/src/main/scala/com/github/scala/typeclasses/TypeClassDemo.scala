package com.github.scala.typeclasses

import scala.math.{Pi, sqrt}

object TypeClassDemo {

    trait Calc[S]{
        def area(shape:S):Double
    }

    case class Circle(radius:Double)

    implicit object CircleCalc extends Calc[Circle] {
        def area(shape:Circle) = 2*shape.radius*Pi;
    }

    case class EQLTriangle(side:Double)

    implicit object EQLTriangleCalc extends Calc[EQLTriangle] {
        def area(shape:EQLTriangle) = (sqrt(3)/4)*shape.side*shape.side
    }

    def a[S](shape:S)(implicit calc: Calc[S])= f"$shape area: ${calc.area(shape)}%2.2f"

    def main(args: Array[String]) {
        //you can only use a(shape) to get area not shape.area
        //this is not so "OO"
        println(a(Circle(2.2)))
        println(a(EQLTriangle(3.9)))
        println(a(Circle(4.5)))
    }
}
