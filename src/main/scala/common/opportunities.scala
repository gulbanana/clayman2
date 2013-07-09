package common
import london._

object opportunities {
  //auto-discard these, unless they are playable
  private val blacklist = Set(
    //useless if they aren't useful
    "Pass the Cat: a wriggling delivery", //the only benefit is -scandal, which i might not need
    "Wanted: Reminders of Brighter Days", //it's not worth keeping around on the off-chance of saving an action
      
    //always useless
    "Help a spy distract an inconvenient tail",             //best reward is 36 jade
    "A meeting of cats",                                    //best reward is 24 clues
    "The notable citizen",                                  //best reward is 83 whispered secrets and it can't be made scandal-safe
    "Shroom-hopping: a quaint sport of the lower classes",  //best reward is 18 '82
    "What profit?",                                         //i don't want to sell my soul! at least not cheaply
    "The tenor's minder",                                   //reward low, dangerous range too small
    "A runaway horse!",                                     //reward low, dangerous range too small
    "Romance and practicality",                             //best reward: 36 whispered secrets
    "Stark villainy"										//best reward: like 30 rostygold
  )
  
  //auto-play these if conditions are met
  private val playlist = Map[String, Character=>Boolean](
    "Pass the Cat: a wriggling delivery" -> {c => c.qualities("Scandal") > 0},
    "Wanted: Reminders of Brighter Days" -> {c => c.items("Incendiary Gossip") >= 25},
    "Altars and alms-houses: the Church" -> {c => c.qualities("Connected: The Church") >= 20 || c.items("Rostygold") >= 10},
    "Mr Wines is holding a sale!" -> {c => c.items("Romantic Notion") >= 80},
    "Lies below the palace" -> {c => c.qualities("Nightmares") < 7} //okish rumour grind- +18 proscribed. reconsider later
  ) withDefaultValue {c:Character => false}
  
  private val takeAdvantage = Map[String, Character=>Unit](
    "Pass the Cat: a wriggling delivery" -> { c => c.chooseBranch("An elaborate strategy") },
    "Wanted: Reminders of Brighter Days" -> { c => c.chooseBranch("The tiniest of classified advertisements") },
    "Altars and alms-houses: the Church" -> { c =>
      if (c.qualities("Connected: The Church") >= 20)
        c.chooseBranch("Attend a private lecture given by the Bishop of Southwark")
      else 
        c.chooseBranch("Attend a church fête on the south bank of the River")
    },
    "Mr Wines is holding a sale!" -> { c => c.chooseBranch("A discount for purchase in bulk") },
    "Lies below the palace" -> { c=> c.chooseBranch() }
  )

  //grind through discards as far as possible
  def mill()(implicit c: Character) = do {
    if (c.opportunities.size < 3 && c.deck > 0) //avoid sending a useless ajax
      c.drawOpportunities()
    for (opportunity <- c.opportunities if (!playlist(opportunity)(c) && blacklist.contains(opportunity)))
      c.discardOpportunity(opportunity)
  } while(c.opportunities.size < 3 && c.deck > 0)
  
  //return whether any are playable
  def can_act()(implicit c: Character) = c.opportunities.map(playlist(_)(c)).reduce(_ || _)

  //play one of the playable ones
  def act_once()(implicit c: Character) {
    val opportunity = c.opportunities.filter(playlist(_)(c)).head
    
    c.playOpportunity(opportunity)
    takeAdvantage(opportunity)(c)
    c.onwards()
    
    mill() //optimisation - don't wait for the next mill to free up the timer
  }
}