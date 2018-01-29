object A3Main extends App {
    val l1:List[Int] = List(1, 2, 3, 4, 2, 3)
    val l2:List[Int] = List(2, 1, 3, 4, 1)
    val l3:List[Int] = List(5, 6, 0, 9, 7)
    val l4:List[String] = List("one", "two")
    val l5:List[String] = List("two", "three")
    val l6:List[String] = List("four", "five")

    // test 1
    val b1:Boolean = A3.test(l1,l2)
    println(b1 + " [corretto: " + false + "]")

    // test 2
    val b2:Boolean = A3.test(l1,l3)
    println(b2 + " [corretto: " + true + "]")

    // test 3
    val b3:Boolean = A3.test(l4,l5)
    println(b3 + " [corretto: " + false + "]")

    // test 4
    val b4:Boolean = A3.test(l4,l6)
    println(b4 + " [corretto: " + true + "]")

    // test 5
    val b5:Boolean = A3.test(l1,l6)
    println(b5 + " [corretto: " + true + "]")
}
