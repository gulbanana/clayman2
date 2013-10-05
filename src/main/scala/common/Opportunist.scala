package common
import api._

class Opportunity(val discardIf: Character => Boolean, val actIf: Character => Boolean, val act: Character=>Unit = _ => throw new Exception("Unplayable card played"))
object Opportunity {
  val defaultAction = (c: Character) => if (!c.branches.isEmpty) c.chooseBranch()
}

object Hold extends Opportunity(_ => false, _ => false)
case class HoldUntil(t: Character => Boolean, a: Character => Unit = Opportunity.defaultAction) extends Opportunity(_ => false, t, a)

object Discard extends Opportunity(_ => true, _ => false)
case class DiscardUnless(t: Character => Boolean, a: Character => Unit = Opportunity.defaultAction) extends Opportunity(c => !t(c), t, a)

case class Play(a: Character=>Unit) extends Opportunity(_ => false, _ => true, a)
object Play extends Play(Opportunity.defaultAction)

class Opportunist(p: Map[String, Opportunity], default: Opportunity = Hold) {
  private val playlist = p withDefaultValue default
  
  //grind through discards as far as possible
  def mill()(implicit c: Character) = do {
    if (c.opportunities.size < c.opportunityCap && c.deck > 0) //avoid sending a useless ajax
      c.drawOpportunities()
      
    for (opportunity <- c.opportunities if (playlist(opportunity).discardIf(c)))
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