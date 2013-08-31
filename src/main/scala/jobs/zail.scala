package jobs
import api._
import common._
import david.unterzee._

object zail extends RepeatedJob {
  def apply(implicit c: Character) = {
    if (c.qualities("Approaching Journey's End") < 9) {
      if (c.qualities("Troubled Waters") < 9) {
        zailing.opportunities_calm.played() or zailing.steam_boldly()
      } else {
        zailing.opportunities_troubled.played() or zailing.steam_prudently()
      }
    } else {
      zailing.opportunities_port.played() || fidgetingWriter(c)
    } 
  }
}