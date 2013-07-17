package common
import london._

class Opportunity(val test: Character => Boolean, val act: Character=>Unit)
case class Conditional(t: Character=>Boolean, a: Character=>Unit) extends Opportunity(t, a)
case class Playable(a: Character=>Unit) extends Opportunity(_ => true, a)
object Unplayable extends Opportunity(_ => false, _ => throw new Exception("Error: Unplayable card played"))
object Trivial extends Opportunity(_ => true, _.chooseBranch())

class Opportunist(playlist: Map[String, Opportunity], blacklist: Set[String]) {
  //grind through discards as far as possible
  def mill()(implicit c: Character) = do {
    if (c.opportunities.size < 3 && c.deck > 0) //avoid sending a useless ajax
      c.drawOpportunities()
    for (opportunity <- c.opportunities if (!playlist(opportunity).test(c) && blacklist.contains(opportunity)))
      c.discardOpportunity(opportunity)
  } while(c.opportunities.size < 3 && c.deck > 0)

  //if any are playable, play one
  def act()(implicit c: Character) = did (c.opportunities.map(playlist(_).test(c)).reduce(_ || _)) {
    val opportunity = c.opportunities.filter(playlist(_).test(c)).head
    
    c.playOpportunity(opportunity)
    playlist(opportunity).act(c)
    c.onwards()
  }
}