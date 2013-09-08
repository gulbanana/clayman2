package common
import api._

class Opportunity(val discardIf: Character => Boolean, val actIf: Character => Boolean, val act: Character=>Unit = throw new Exception("Unplayable card played"))

object Hold extends Opportunity(_ => false, _ => false)
case class HoldUntil(t: Character => Boolean, a: Character => Unit) extends Opportunity(_ => false, t, a)

object Discard extends Opportunity(_ => true, _ => false)
case class DiscardUnless(t: Character => Boolean, a: Character => Unit) extends Opportunity(c => !t(c), t, a)

case class Playable(a: Character=>Unit = _.chooseBranch()) extends Opportunity(_ => false, _ => true, a)
object Trivial extends Opportunity(_ => false, _ => true, _.chooseBranch())
object Autofire extends Opportunity(_ => false, _ => true, _ => ())

class Opportunist(playlist: Map[String, Opportunity], blacklist: Set[String]) {
  //grind through discards as far as possible
  def mill()(implicit c: Character) = do {
    if (c.opportunities.size < c.opportunityCap && c.deck > 0) //avoid sending a useless ajax
      c.drawOpportunities()
      
    for (opportunity <- c.opportunities if (!playlist(opportunity).actIf(c) && blacklist.contains(opportunity)))
      c.discardOpportunity(opportunity)
      
  } while(c.opportunities.size < c.opportunityCap && c.deck > 0)

  //if any are playable, play one
  def act()(implicit c: Character) = did (c.opportunities.nonEmpty && c.opportunities.map(playlist(_).actIf(c)).reduce(_ || _)) {
    val opportunity = c.opportunities.filter(playlist(_).actIf(c)).head
    
    c.playOpportunity(opportunity)
    playlist(opportunity).act(c)
    c.onwards()
  }
  
  //attempt to spend an action
  def played()(implicit c: Character) = {
    mill()
    
    did (act()) {
      mill()
    }
  }
}