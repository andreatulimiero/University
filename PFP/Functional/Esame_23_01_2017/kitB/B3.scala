object B3 {
    def test[T](l1:List[T], l2:List[T]):Boolean = {
      l1.distinct.intersect(l2.distinct).size == l1.distinct.size
    }
}
