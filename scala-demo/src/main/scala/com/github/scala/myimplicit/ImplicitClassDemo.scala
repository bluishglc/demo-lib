package com.github.scala.myimplicit

/**
  * A demo about scala implicit type conversion.
  * @author Laurence Geng
  */
object ImplicitClassDemo {

    implicit class MyImplicitTypeConversion(val str: String){
        def strToInt = str.toInt
    }

    def main(args: Array[String]) {
        //compile error!
        //val max = math.max("1", 2);
        import com.github.scala.myimplicit.ImplicitDefDemo.MyImplicitTypeConversion._
        val max = math.max("1", 2);
        println(max)
    }
}
