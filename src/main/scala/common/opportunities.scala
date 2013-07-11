package common
import london._

object opportunities {
  //auto-discard these, unless they are playable
  private val blacklist = Set(
    //useless if they aren't useful
    "Pass the Cat: a wriggling delivery", //the only benefit is -scandal, which i might not need
    "Wanted: Reminders of Brighter Days", //it's not worth keeping around on the off-chance of saving an action
    "The Ambassador's ball",              //might not be in the range to play it - only gives a confident smile
      
    //always useless
    "Help a spy distract an inconvenient tail",             //best reward: 36 jade
    "A meeting of cats",                                    //best reward: 24 clues
    "The notable citizen",                                  //best reward: 83 whispered secrets and it can't be made scandal-safe
    "Shroom-hopping: a quaint sport of the lower classes",  //best reward: 18 '82
    "What profit?",                                         //i don't want to sell my soul! at least not cheaply
    "The tenor's minder",                                   //reward low, dangerous range too small
    "A runaway horse!",                                     //reward low, dangerous range too small
    "Romance and practicality",                             //best reward: 36 whispered secrets
    "Stark villainy",                   										//best reward: like 30 rostygold
    "The marriage of inconvenience"                         //best reward: 50 secrets
  )
  
  //auto-play these if conditions are met
  private val playlist = Map[String, Character=>Boolean](
    "Pass the Cat: a wriggling delivery" -> { c => c.qualities("Scandal") > 0 },
    "Wanted: Reminders of Brighter Days" -> { c => c.items("Incendiary Gossip") >= 25 },
    "Altars and alms-houses: the Church" -> { c => c.qualities("Connected: The Church") >= 20 || c.items("Rostygold") >= 10 },
    "Mr Wines is holding a sale!" -> { c => c.items("Romantic Notion") >= 80 },
    "Lies below the Palace" -> { c => c.qualities("Nightmares") < 7 }, //okish Watchful/rumour grind- +18 proscribed. reconsider later
    "The Tower of Sparrows" -> { c => true },
    "The Tower of Sleeping Giants" -> { c => true },
    "The Ambassador's ball" -> { c => c.persuasive > 80 && c.persuasive < 119 }
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
    "Lies below the Palace" -> { c=> c.chooseBranch() },
    "The Tower of Sparrows" -> { c => c.chooseBranch("Settle down to a game of cards") },
    "The Tower of Sleeping Giants" -> { c =>
      if (c.items("An Infernal Contract") < 100)
        c.chooseBranch("The owner")
      else 
        c.chooseBranch("Examine the stock") 
    },
    "The Ambassador's ball" -> { c => c.chooseBranch("Making a point of not making a point") }
  )

  //grind through discards as far as possible
  def mill()(implicit c: Character) = do {
    if (c.opportunities.size < 3 && c.deck > 0) //avoid sending a useless ajax
      c.drawOpportunities()
    for (opportunity <- c.opportunities if (!playlist(opportunity)(c) && blacklist.contains(opportunity)))
      c.discardOpportunity(opportunity)
  } while(c.opportunities.size < 3 && c.deck > 0)

  //if any are playable, play one
  def act()(implicit c: Character) = did (c.opportunities.map(playlist(_)(c)).reduce(_ || _)) {
    val opportunity = c.opportunities.filter(playlist(_)(c)).head
    
    c.playOpportunity(opportunity)
    takeAdvantage(opportunity)(c)
    c.onwards()
  }
}