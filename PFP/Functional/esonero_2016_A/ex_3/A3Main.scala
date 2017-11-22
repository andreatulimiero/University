object A3Main extends App { 
  // parte I: definizione della gerarchia e conversione a stringa di exp
  val e:Exp = And(Or(X(), Y()), Not(Y()))
  println(e + " [corretto: and(or(x,y),not(y))")

  // parte II: valutazione di espressioni booleane
  for ( x <- List(true, false); y <- List(true, false) ) {
          val b:Boolean = e(x,y)
          println("e("+x+","+y+")="+b+" [corretto: "+((x||y) && !y)+"]")
  } 
  /**
  // parte III: relazione se e solo se (<=>)
  val e1:Exp = And(Or(X(), Y()), Not(Y()))
  val e2:Exp = Not(Or(And(Not(X()), Not(Y())),Y()))
  val e3:Exp = And(Or(X(), Not(X())),Or(Y(), Not(Y())))
  println("e1 <=> e2: "+(e1 <=> e2)+" [corretto: true]")
  println("e1 <=> e3: "+(e1 <=> e3)+" [corretto: false]")
  println("True() <=> e3: "+(True() <=> e3)+" [corretto: true]")
  println("True() <=> False(): "+(True() <=> False())+             " [corretto: false]")
  **/
} 