
object Main extends App {
case class Studente(val name:String, val age:Int, val exams:List[String])
val q = List(
              Studente("Marco", 23, List("PFP","SC")),
              Studente("Laura", 19, List("SC", "FI1", "PFP")),
              Studente("Stefano", 23, List("SC", "FI1")),
              Studente("Marco", 25, List("SC", "FI1", "FI2")),
              Studente("Paola", 22, List("SC", "PFP"))
            )

val ageInRange: (Int, Int, Int) => Boolean = (age, a, b) => age >= a && age <= b 
val query1 = for (
                  s <- q.filter((s) => ageInRange(s.age, 20, 24));
                  if s.exams.contains("PFP")
                  ) yield s
println(query1)

val query2 = for (
                  s <- q.filter((s) => s.age < 24);
                  if s.exams.length >= 2
                  ) yield s
println(query2)
}