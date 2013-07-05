package jobs
import london._
import common._

//just a test job
object login extends OneManJob {
  def apply(implicit c: Character) {
    println("%d actions available.".format(c.actions))
    
    grind.palace_wines()
    
    println("%d actions available.".format(c.actions))
  }
}