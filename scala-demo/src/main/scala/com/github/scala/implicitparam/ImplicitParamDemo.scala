package com.github.scala.implicitparam;

/**
 * Created by lichao.geng on 3/12/2016.
 */
object ImplicitParamDemo {

    object Greeter{
        def greet(name:String)(implicit prompt: String) {
            println("Welcome, " + name + ". The System is ready.")
            println(prompt)
        }
    }

    def main(args: Array[String]) {

        implicit val prompt = ">"

        Greeter.greet("admin")
    }

}
