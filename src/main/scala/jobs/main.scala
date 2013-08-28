package jobs
import api._
import common._
import david._

object main extends BufferedJob {
  def apply(implicit c: Character) = did (c.location == Areas.NewNewgate) {
    prison.opportunities.played() or prison.reduce_suspicion()
  } or (c.location == Areas.TombColonies) {
    colonies.opportunities.played() or colonies.reduce_scandal()
  } or london.standardGrind() 
}