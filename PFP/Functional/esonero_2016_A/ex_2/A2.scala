case class Studente(val nome:String, val età:Int, val esami:List[String]) 

object A2 {

  def query1(l:List[Studente]) = {
    val ages = l.map((s) => s.età)
    val avgAge = ages.reduce((a, b) => a + b) / ages.length
    for (
          s <- l
          if s.età < avgAge && s.esami.length >= 3
        ) yield s
  }

  def query2(l:List[Studente]) = {
    val examsLists:List[List[String]] = l.map((s) => s.esami)
    
    val targetExams = for (
                            examsList <- examsLists;
                            e <- examsList;
                            if (l.filter((s) => s.esami.contains(e)).length >= 2)
                          ) yield e
    def removeDuplicates(l:List[String]):List[String] = {
          l match {
            case Nil => Nil
            case h::Nil => List(h)
            case h::t => if (t.contains(h)) removeDuplicates(t) else h::removeDuplicates(t)
          }
    }
    removeDuplicates(targetExams)
  }

}

object A2Main extends App {
  val q = List(
                 Studente("Marco", 24, List("PFP","SC")),
                 Studente("Laura", 19, List("SC", "FI1", "PFP", "DB")),
                 Studente("Stefano", 23, List("SC", "FI1")),
                 Studente("Marco", 25, List("SC", "FI1", "FI2")),
                 Studente("Paola", 21, List("SC", "PFP")),
                 Studente("Lucia", 18, List("SC", "PFP", "OOP"))
             ) 
     // query1 estrae la lista di tutti gli studenti che hanno 
     // età inferiore alla media e hanno sostenuto almeno tre esami
     val query1:List[Studente] = A2.query1(q)
     println(query1.map(_.nome))
     println("--> [corretto: Laura e Lucia]") 

     // query2 estrae la lista di tutti gli esami che sono stati
     // sostenuti da almeno due studenti
     val query2:List[String] = A2.query2(q)
     println(query2)
     println("--> [corretto: SC, FI1 e PFP]") 
}