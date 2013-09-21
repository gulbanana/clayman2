package jobs.london
import api._
import common._
import david._

//eat all opportunties and actions until we run out of one or the other
object playCards extends RepeatedJob {
  def apply(implicit c: Character) = london.opportunities.played()
}