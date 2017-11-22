object B2 {
    def maxSubSeq[T](l:List[T], p:T => Boolean):List[T] = {
        def maxSubSeqAux(l:List[T], p:T => Boolean):List[T] = {
	  l match {
	    case Nil => Nil
	    case h::t => if (p(h)) h::maxSubSeqAux(t, p) else Nil
	  }
	}

	val subs = for (
			i <- 1 until (l.size)
			) yield maxSubSeqAux(l.splitAt(i)._2, p)
	subs.reduce((a,b) => if (a.size > b.size) a else b)
    }
}
