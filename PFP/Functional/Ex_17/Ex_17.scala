object Ex_17 extends App {
  def allDistinct[T<%Ordered[T]](l:List[T]):Boolean = {
    l match {
      case Nil => true
      case h::Nil => true
      case h::t => {
        val ord_l:List[T] = l.sorted:List[T]
        val slider = ord_l.sliding(2)
        val duplicates = slider.filter((e) => e(0) == e(1))
        duplicates.isEmpty
      }
    }
  }

  val a = 99::List.range(0, 100)
  println(allDistinct(a))
  val b = List.range(0, 100)
  println(allDistinct(b))
  val c = List(2)
  println(allDistinct(c))
  val d = Nil
  println(allDistinct(d))
}