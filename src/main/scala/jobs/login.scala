package jobs
import london._
import common._

//just a test job
object login extends OneManJob {
  def apply(implicit c: Character) {
    c.drawOpportunities()
    println(c.opportunities)
  }
}