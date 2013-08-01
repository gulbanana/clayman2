package jobs
import london._
import common._
import david._

//eat all opportunties and actions until we run out of one or the other
object playCards extends Job with Duty {
  def apply() = with_character(c => while (c.actions > 0 && apply(c)) ())
  
  def apply(implicit c: Character) = opportunities.london.played()
}