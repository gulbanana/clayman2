package jobs
import london._
import common._
import david._

//eat all opportunties and actions until we run out of one or the other
object playCards extends Job {
  def apply() = with_character { implicit c => 
    val deck = opportunities.london
    deck.mill()
    
    while (c.actions > 0 && deck.act())
      deck.mill()
  }
}