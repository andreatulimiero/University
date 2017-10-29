object Main extends App {

    def concatena(f1:Double => Double, f2:Double => Double, f3:Double => Double, a:Double, b:Double): Double => Double = {
        x:Double => if (x < a) f1(x) else if (x >= a && x <= b) f2(x) else f3(x)
    }

    val inc: Double => Double = (x) => x + 1
    val dec: Double => Double = (x) => x - 1
    val neg: Double => Double = (x) => -x
    val variant = concatena(inc, dec, neg, 10, 20)
    println(variant(5))
    println(variant(10))
    println(variant(21))
}