sealed abstract class Exp {

  def apply(x:Boolean):Boolean = {
    this match {
      case Not(e) => e(x)
      case X() => x
      case Y() => x
      case _ => println("Something went wrong " + this); false
    }
  }

  def apply(x:Boolean, y:Boolean):Boolean = {
    this match {
      case And(el, er) => el(x) && er(y)
      case Or(el, er) => el(x) && er(y)
      case _ => println("Something went wrong " + this); false
    }
  }

}

case class And(val el:Exp, val er:Exp) extends Exp {

  override def toString = "and(" + X + ", " + Y + ")"

}

case class Or (val el:Exp, val er:Exp) extends Exp() {

  override def toString = "or(" + el + ", " + er + ")"

}

case class Not (val e:Exp) extends Exp() {
  
  override def toString = "not(" + e + ")"

}

case class X() extends Exp {

  override def toString = "x"

}

case class Y() extends Exp {

  override def toString = "y"

}