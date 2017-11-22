import MyRichSet._ 
object A1Main extends App { 
  val s1 = Set(1,2,3,4)     
  val s2 = Set(4,3,5,7) 
  val s3:Set[Int] = s1 + s2 // unione insiemi
  println(s3 + " [corretto: 1,2,3,4,5,7 (in qualsiasi ordine)]") 
  val s4:Set[Int] = s1 - s2 // differenza insiemi
  println(s4 + " [corretto: 1,2  (in qualsiasi ordine)]") 
}