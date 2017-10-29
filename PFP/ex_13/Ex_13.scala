object Ex_13 extends App {
  def equal(a:List[Int], b:List[Int]):Boolean = {
    (a, b) match {
      case (Nil, Nil) => true
      case (Nil, _) => false
      case (_, Nil) => false
      case (_, _) => if (a.head == b.head) equal(a.tail, b.tail) else false
    }
  }

  val a = Nil
  val b = Nil
  print(equal(a, b))
}