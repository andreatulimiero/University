object B3Main extends App {
    val l1:List[Int] = List(1, 2, 3, 4, 2, 3)
    val l2:List[Int] = List(2, 1, 3, 4, 1)
    val l3:List[Int] = List(2, 1, 3, 1, 9)

    // test 1
    val b1:Boolean = B3.test(l1,l2)
    println(b1 + " [corretto: " + true + "]")

    // test 2
    val b2:Boolean = B3.test(l1,l3)
    println(b2 + " [corretto: " + false + "]")
}
