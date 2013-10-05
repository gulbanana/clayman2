package david.unterzee
import api._
import common._
import david._

object bullboneIsland extends OneManJob {
  def apply(implicit c: Character) = {
    gear.watchful()
    did (c.qualities("Orthos is Coming!") < 4) {
      islands.bullbone_T0()
    } or (c.qualities("Orthos is Coming!") < 6) {
      islands.bullbone_T4()
    } or (c.qualities("Orthos is Coming!") == 6) {
      islands.bullbone_T6()
    } or (c.qualities("Orthos is Coming!") == 7) {
      islands.bullbone_T7()
    } or (c.qualities("Orthos is Coming!") == 8) {
      islands.bullbone_T8()
    } or (c.qualities("Orthos is Coming!") == 9) {
      islands.bullbone_T9()
    } or (c.qualities("Orthos is Coming!") == 10) {
      islands.bullbone_T10()
    }
  }
}