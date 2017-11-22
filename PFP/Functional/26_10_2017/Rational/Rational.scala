object Rational {

  def apply(n:Int, d:Int) = new Rational(n, d)

  implicit def int2Rational(i:Int) = new Rational(i, 1)
  implicit def rationalToDouble(r:Rational) = r.n.toDouble/r.d
}

class Rational(_n:Int, _d:Int) {

  private def sgn(x:Int, y:Int) = if (x*y < 0) "-" else ""
  private def abs(x:Int) = if (x < 0) -x else x
  private def mcd(x:Int, y:Int):Int = {
    if (y == 0) x else mcd(y, x%y) // Since it's only useful for class' internals
  }

  val n = _n/mcd(_n, _d)
  val d = _d/mcd(_n, _d)

  override def toString = sgn(n,d) + abs(n) + "/" + abs(d)

  def +(r:Rational) = Rational(n*r.d + r.n*d, d*r.d)
}