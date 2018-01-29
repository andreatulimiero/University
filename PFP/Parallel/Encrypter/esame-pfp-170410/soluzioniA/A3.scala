object A3 {
    def test[T](l1:List[T], l2:List[T]):Boolean = {
        l1.intersect(l2).size == 0
    }
}
