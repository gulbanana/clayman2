package jobs
import london._
import common._
import david._

//just a test script
object login extends Job {
  def apply() = with_character { implicit c =>
    gear.shadowy()
  }
}