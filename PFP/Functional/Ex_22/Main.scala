object Main extends App {

    def intersection[T](l1:List[T], l2:List[T]):List[T] = {
        
        (l1.distinct, l2.distinct) match {
            case (Nil, Nil) => Nil
            case (Nil, l) => Nil
            case (l, Nil) => Nil
            case (l1, l2) => l1.filter(l2.contains(_))
        }
    }

    val l1 = 5::List.range(0, 10, 2)
    val l2 = 4::List.range(0, 10, 3)
    println(l1) // List(5, 0, 2, 4, 6, 8)
    println(l2) // List(4, 0, 3, 6, 9)
    println(intersection(l1, l2)) // List(0, 4, 6)
    println(intersection(Nil, l2)) // List()
    println(intersection(l1, Nil)) // List()
    println(intersection(Nil, Nil)) // List()
}