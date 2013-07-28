package jobs
import london._
import common._
import david._

//eat all opportunties and actions until we run out of one or the other
object playCards extends Job with Duty {
  def apply() = with_character(c => repeat(c, apply(_)))
  
  def apply(implicit c: Character) = {
    val deck = opportunities.london
    deck.mill()
    
    did (deck.act()) {
      deck.mill()
    }
  }
}