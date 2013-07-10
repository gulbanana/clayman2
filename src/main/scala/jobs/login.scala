package jobs
import london._
import common._

//just a test job
object login extends OneManJob {
  def apply(implicit c: Character) {
    gear.shadowy()
  }
}