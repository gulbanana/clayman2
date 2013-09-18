package jobs.unterzee
import api._
import common._
import david.unterzee._

object bullboneIsland extends RepeatedJob {
  def apply(implicit c: Character) = did (c.qualities("Orthos is Coming!") < 0) {
    islands.bullbone_T1()
  } or (c.qualities("Orthos is Coming!") > 100) {
    islands.bullbone_T1()
  }
}