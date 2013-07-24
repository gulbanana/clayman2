package jobs
import london._
import common._

//eat all opportunties and actions until we run out of one or the other
object playCards extends OneManJob {
  def apply(implicit c: Character) {
    val deck = opportunities.london
    deck.mill()
    
    while (c.actions > 0 && deck.act())
      deck.mill()
  }
}