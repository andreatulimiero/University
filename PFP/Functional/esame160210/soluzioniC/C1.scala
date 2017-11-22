sealed abstract class Tree() {
    def minMax = {
        val (v1, s1) = this.min
        val (v2, s2) = this.max
        (v1, v2, s1 + s2)
    }
    def min: (Int,Int) = this match {
        case E() => (Integer.MAX_VALUE, 0)
        case T(l:Tree, x:Int, r:Tree) => {
            val (m,s) = l.min
            (x min m, s+1)
        }
    }
    def max: (Int,Int) = this match {
        case E() => (Integer.MIN_VALUE, 0)
        case T(l:Tree, x:Int, r:Tree) => {
            val (m,s) = r.max
            (x max m, s+1)
        }
    }
}

case class E() extends Tree()
case class T(l:Tree, x:Int, r:Tree) extends Tree()

