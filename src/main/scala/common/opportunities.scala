package common
import london._

object opportunities {
  case class Playable(test: Character=>Boolean, act: Character=>Unit) 
  object Unplayable extends Playable(_ => false, null)
  object Trivial extends Playable(_ => true, _.chooseBranch())
  
  //auto-discard these, unless they are playable
  private val blacklist = Set(
    //useless if they aren't useful
    "Pass the Cat: a wriggling delivery", //the only benefit is -scandal, which i might not need
    "Wanted: Reminders of Brighter Days", //it's not worth keeping around on the off-chance of saving an action
    "The Ambassador's ball",              //might not be in the range to play it - only gives a confident smile
    "Whispers from the Surface: The Great Game",  //can't use it to grind
    "A consideration for services rendered",      //not worth grinding souls for
    "Graffiti with a sting",					  //only useful when Counting the Days
    "The Ways of the Flit",					//street signs are valuable, though it's difficult 
      
    //always useless
    "Help a spy distract an inconvenient tail",             //best reward: 36 jade
    "A meeting of cats",                                    //best reward: 24 clues
    "The notable citizen",                                  //best reward: 83 whispered secrets and it can't be made scandal-safe
    "Shroom-hopping: a quaint sport of the lower classes",  //best reward: 18 '82
    "What profit?",                                         //i don't want to sell my soul! at least not cheaply
    "The tenor's minder",                                   //reward low, dangerous range too small
    "A runaway horse!",                                     //reward low, dangerous range too small
    "Romance and practicality",                             //best reward: 36 whispered secrets
    "Stark villainy",                   					//best reward: like 30 rostygold
    "The marriage of inconvenience",                        //best reward: 50 secrets
    "The Ways of the University",
    "The Ways of the Forgotten Quarter",
    "The Ways of Wolfstack Docks",
    "The Ways of the Shuttered Palace"
  )
  
  //auto-play these if conditions are met
  private val playlist = Map[String, Playable](
    //Lodgings
    "The Tower of Sparrows" -> Playable(_ => true, _.chooseBranch("Settle down to a game of cards")),
    "The Tower of Sleeping Giants" -> Playable(_ => true, c =>
      if (c.items("An Infernal Contract") < 100)
        c.chooseBranch("The owner")
      else 
        c.chooseBranch("Examine the stock") 
    ),
    "The Sleepless Tower" -> Playable(_ => true, _.chooseBranch("Spores and fangs")),
    "The Lofty Tower" -> Unplayable,
    "The Western Tower" -> Unplayable,
    
    //Connections
    "Altars and alms-houses: the Church" -> Playable(c => c.qualities("Connected: The Church") >= 20 || c.items("Rostygold") >= 10, c =>
      if (c.qualities("Connected: The Church") >= 20)
        c.chooseBranch("Attend a private lecture given by the Bishop of Southwark")
      else 
        c.chooseBranch("Attend a church fête on the south bank of the River")
    ),
    "Court and Cell: the Constables" -> Playable(c => c.qualities("Connected: Constables") >= 15 || c.items("Rostygold") >= 10, implicit c =>
      if (c.qualities("Connected: Constables") >= 15) {
        gear.watchful()
        c.chooseBranch("Attend a class given by the Implacable Detective")
      } else {
        c.chooseBranch("A small donation")
      }
    ),
    "The Demi-Monde: Bohemians" -> Playable(_.qualities("Connected: Bohemian") >= 3, _.chooseBranch("Take tea with a Reclusive Novelist")),
    "Bandages and Dust: The Tomb-Colonies" -> Playable(_.qualities("Connected: The Tomb-Colonies") >= 3, { implicit c => gear.dangerous(); c.chooseBranch("Spar with a Black Ribbon Duellist") }),
    "Whispers from the Surface: The Great Game" -> Playable(c => (c.qualities("Connected: The Great Game") >= 10 && c.watchful <= 70) || c.qualities("Connected: The Great Game") >= 20, c =>
      if (c.watchful > 70)
        c.chooseBranch("Learn more at the carnival")
      else 
        c.chooseBranch("Get some work done at Clathermont's tattoo parlour")
    ),
    "The Roof-Tops: Urchins" -> Playable(c => (c.qualities("Connected: Urchins") >= 3 && c.shadowy < 71) || c.items("Glim") >= 20 || c.items("Lucky Weasel") >= 1, c => 
      if (c.shadowy > 70 && c.items("Glim") >= 20)
        c.chooseBranch("Out you go, longshanks")
      else if (c.shadowy < 71 && c.qualities("Connected: Urchins") >= 3)
        c.chooseBranch("Run the rooftops with the urchins")
      else
        c.chooseBranch("In the shadow of All Christs Spire")
    ),
    "Burning Shadows: the Devils of London" -> Playable(c => true, c => 
      if (c.watchful > 70 && c.qualities("Connected: Hell") > 2) {
        c.chooseBranch("Speak with a senior deviless")
      } else {
        c.chooseBranch("Attend a lecture at the Brass Embassy")
      }
    ),
    "Gunpowder and Zeal: the Revolutionaries" -> Playable(c => true, _.chooseBranch("Taking a walk down gin lane")),
    
    //Misc
    "Pass the Cat: a wriggling delivery" -> Playable(_.qualities("Scandal") > 0, _.chooseBranch("An elaborate strategy")),
    "Wanted: Reminders of Brighter Days" -> Playable(_.items("Incendiary Gossip") >= 25, _.chooseBranch("The tiniest of classified advertisements")),
    "Mr Wines is holding a sale!" -> Playable(_.items("Romantic Notion") >= 80, _.chooseBranch("A discount for purchase in bulk")),
    "Lies below the Palace" -> Playable(_.qualities("Nightmares") < 7, _.chooseBranch()), //okish Watchful/rumour grind- +18 proscribed. reconsider later
    "The Ambassador's ball" -> Playable(c => c.persuasive > 80 && c.persuasive < 119, _.chooseBranch("Making a point of not making a point")),
    "Going gentle" -> Playable(_ => true, { implicit c => gear.dangerous(); c.chooseBranch("Break him out!") }),
    "A night at the carnival" -> Playable(_ => true, { implicit c => gear.dangerous(); c.chooseBranch("There's always something") }),
    "A consideration for services rendered" -> Playable(_.items("Soul") > 0, _.chooseBranch()),
    "A parliament of bats" -> Playable(_ => true, _.chooseBranch("Release a bat into the cloud")),
    "The Ways of the Flit" -> Playable(_.shadowy >= 70, _.chooseBranch("An old street sign")),
    
    //Counting the Days
    "The Awful Temptation of Money" -> Trivial,
    "Graffiti with a sting" -> Playable(_.qualities("Counting the Days") >= 10, _.chooseBranch("Ask someone else what they saw"))
  ) withDefaultValue Unplayable

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
