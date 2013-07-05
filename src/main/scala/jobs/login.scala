package jobs
import london._
import common._

//just a test job
object login extends OneManJob {
  def apply(implicit c: Character) {
    println("* %d/%d actions.".format(c.actions, c.actionCap))
    println("* Dangerous: %d".format(c.dangerous))
    println("* Wounds: %d".format(c.qualities("Wounds")))
    println("* Silk: %d".format(c.items("Silk Scrap")))
  }
}