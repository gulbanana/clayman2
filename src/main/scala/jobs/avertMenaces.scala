package jobs
import london._
import common._
import david._

//eat all opportunties and actions until we run out of one or the other
object avertMenaces extends RepeatedJob {
  def apply(implicit c: Character) = escapePrison
  
  def escapePrison(implicit c: Character) = did (c.location == Areas.NewNewgate) {
    prison.opportunities.played() or prison.reduce_suspicion()
  }
}