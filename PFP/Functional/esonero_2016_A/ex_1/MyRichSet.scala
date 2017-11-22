object MyRichSet {

  implicit def Set2MyRichSet(set:Set[Int]) = new MyRichSet(set)

}

class MyRichSet(val _set:Set[Int]) {

  def +(otherSet:Set[Int]) = _set union otherSet

  def -(otherSet:Set[Int]) = _set diff otherSet

}