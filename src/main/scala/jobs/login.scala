package jobs
import london._
import common._

//just a test job
object login extends OneManJob {
  def apply(implicit c: Character) {
    println("* %d/%d actions.".format(c.actions, c.actionCap))
    println("* Persuasive: %d".format(c.persuasive))
    println("* Scandal: %d".format(c.qualities("Scandal")))
    println("* Greyfields 1882: %d".format(c.items("Greyfields 1882")))
    
    //grind.palace_wines()
    
    println("* %d/%d actions.".format(c.actions, c.actionCap))
    println("* Persuasive: %d".format(c.persuasive))
    println("* Scandal: %d".format(c.qualities("Scandal")))
    println("* Greyfields 1882: %d".format(c.items("Greyfields 1882")))
  }
}