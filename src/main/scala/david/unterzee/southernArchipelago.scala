package david.unterzee
import api._
import common._
import david._

object southernArchipelago extends RepeatedJob {
  def apply(implicit c: Character) = {
    if (c.qualities("Approaching Journey's End") < 9) {
      if (c.qualities("Troubled Waters") < 9) {
        zailing.opportunities_calm.played() or zailing.archipelago_fast()
      } else if (c.qualities("Troubled Waters") < 11) {
        zailing.opportunities_troubled.played() or zailing.archipelago_safe()
      } else {
        zailing.opportunities_fury.played()
      }
    } else {
      zailing.opportunities_port.played() or (c.items("Tale of Terror!!") > 0) {
        c.useItem("Tale of Terror!!")
        c.chooseBranch("There's something familiar about this tale...")
      }
    } 
  }
}