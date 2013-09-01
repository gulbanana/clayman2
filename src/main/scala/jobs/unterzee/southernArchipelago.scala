package jobs.unterzee
import api._
import common._
import david.unterzee._

object southernArchipelago extends RepeatedJob {
  def apply(implicit c: Character) = {
    if (c.qualities("Approaching Journey's End") < 9) {
      if (c.qualities("Troubled Waters") < 9) {
        zailing.opportunities_calm.played() or zailing.archipelago_fast()
      } else {
        zailing.opportunities_troubled.played() or zailing.archipelago_safe()
      }
    } else {
      zailing.opportunities_port.played() || jobs.fidgetingWriter(c)
    } 
  }
}