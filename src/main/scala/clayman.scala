import jobs._

object Clayman {
  val jobs = Map(
    "grind" -> grind,
    "login" -> login
  )
  
  def main(args: Array[String]) {
    common.with_character { c =>
  	  jobs(args(0))(c)
    }
  }
}
