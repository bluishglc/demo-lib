package com.github.scala.myimplicit

/**
  * A demo about scala implicit type conversion.
  * @author Laurence Geng
  */
object ImplicitDefDemo {

    object MyImplicitTypeConversion {
        implicit def strToInt(str: String) = str.toInt
    }

    def main(args: Array[String]) {
        //compile error!
        //val max = math.max("1", 2);
        import MyImplicitTypeConversion.strToInt
        val max = math.max("1", 2);
        println(max)
    }
}
