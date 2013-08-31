package jobs
import api._
import common._
import david._

object zail extends RepeatedJob {
  def apply(implicit c: Character) = {
    if (c.qualities("Approaching Journey's End") < 9) {
      unterzee.zailing.opportunities.played() or (c.qualities("Troubled Waters") < 9) {
        unterzee.zailing.steam_boldly()
      } or {
        unterzee.zailing.steam_prudently()
      }
    } else {
      fidgetingWriter(c)
    }
  }
}