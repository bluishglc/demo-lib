package com.github.scala.primaryconstructor

/**
  * Created by lichao.geng on 3/15/2016.
  */
object PrimaryConstructorDemo {
    //Person1 is to show: primary constructor consists of not only class args list but also all runnable part in class body.
    class Person1(var firstName: String, var lastName: String) {
        println("the constructor begins")
        // some class fields
        private val HOME = "/root"
        var age = 30
        // some methods
        override def toString = s"$firstName $lastName is $age years old"
        def printHome { println(s"HOME = $HOME") }
        def printFullName { println(this) } // uses toString
        printHome
        printFullName
        println("still in the constructor")
    }

    // Person2 is to show: the visibility of class fields
    class Person2 {
        var age = 30
        val gender = "male"
        private val healthy = false
    }

    // Person3 is to show: the visibility of primary constructor args
    class Person3(var age:Int,val gender:String,private val healthy:Boolean)

    // Person4 is to show: change visibility for primary constructor args
    class Person4(private var _age:Int) {
        def age = _age // this is getter
        def age_=(newAge: Int) = _age = newAge //this is setter
    }

    def main(args: Array[String]) {
        val p1 = new Person1("Tome","White")
        println("---------------------------------")
        val p2 = new Person2
        println(p2.age)
        p2.age = 40;
        println(p2.age)
        println(p2.gender)
        //error, no setter for gender.
        //p2.gender = false
        //error, invisible out of class.
        //println(p2.healthy)
        println("---------------------------------")
        val p3 = new Person3(30,"male",false)
        println(p3.age)
        p3.age = 40;
        println(p3.age)
        println(p3.gender)
        //error, no setter for gender.
        //p3.gender = false
        //error, invisible out of class.
        //println(p3.healthy)
        println("---------------------------------")
        val p4 = new Person4(30)
        println(p4.age)
        p4.age = 40
        println(p4.age)
    }
}
