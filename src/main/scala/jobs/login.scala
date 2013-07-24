package jobs
import london._
import common._
import david._
import common.OneManJob

//just a test job
object login extends OneManJob {
  def apply(implicit c: Character) {
    gear.shadowy()
  }
}