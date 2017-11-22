object B2Main extends App {
    // test 1: trova sottosequenza più lunga positiva
    val l1:List[Int] = List(9, 4, -1, -3, 7, 3, 6, -1)
    val m1:List[Int] = B2.maxSubSeq(l1, (x:Int) => x > 0);
    println(m1 + " [corretto: " + List(7,3,6) + "]")

    // test 2: trova sottosequenza più lunga di stringhe con < 3 caratteri
    val l2:List[String] = List("force", "may", "the", "jedi", "obi")
    val m2:List[String] = B2.maxSubSeq(l2, (x:String) => x.length <= 3);
    println(m2 + " [corretto: " + List("may", "the") + "]")
}
