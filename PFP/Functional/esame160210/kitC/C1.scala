// completare questo file con la soluzione...

sealed abstract class Tree()
case class E() extends Tree()
case class T(l:Tree, x:Int, r:Tree) extends Tree()
