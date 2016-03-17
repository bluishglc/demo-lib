package com.github.scala.typeclasses

import scala.math.{Pi, sqrt}

object TypeClassDemo2 {

    trait Car

    trait Transmissive[C]{
        def shift(Car:C)
    }

    case class Bus(seats:Int) extends Car

    implicit object ATBus extends Transmissive[Bus] {
        def shift(car:Bus) = println(s"This is a Bus, seats: ${car.seats} , now shift automatically!");
    }

//    //Error: ambiguous implicit values:
//    implicit object MTBus extends Transmissive[Bus] {
//        def shift(car:Bus) = println(s"This is a Bus, seats: ${car.seats} , now shift manually!");
//    }

    case class Truck(load:Double) extends Car

    implicit object ATTruck extends Transmissive[Truck] {
        def shift(car:Truck) = println(s"This is a Truck, load: ${car.load} T, now shift automatically!");
    }

//    //Error: ambiguous implicit values:
//    implicit object MTTruck extends Transmissive[Truck] {
//        def shift(car:Truck) = println(s"This is a Truck, load: ${car.load} T, now shift manually!");
//    }

    def shift_func[C](car:C)(implicit t: Transmissive[C]) = t.shift(car)

    def main(args: Array[String]) {
        shift_func(Bus(10))
        shift_func(Truck(1))
    }
}
