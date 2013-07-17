package common
import london._

object opportunities {  
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
  private val playlist = Map[String, Opportunity](
    //Lodgings
    "The Tower of Sparrows" -> Playable(_.chooseBranch("Settle down to a game of cards")),
    "The Tower of Sleeping Giants" -> Playable(c =>
      if (c.items("An Infernal Contract") < 100)
        c.chooseBranch("The owner")
      else 
        c.chooseBranch("Examine the stock") 
    ),
    "The Sleepless Tower" -> Playable(_.chooseBranch("Spores and fangs")),
    "The Lofty Tower" -> Unplayable,
    "The Western Tower" -> Unplayable,
    
    //Connections
    "Altars and alms-houses: the Church" -> Conditional(c => c.qualities("Connected: The Church") >= 20 || c.items("Rostygold") >= 10, c =>
      if (c.qualities("Connected: The Church") >= 20)
        c.chooseBranch("Attend a private lecture given by the Bishop of Southwark")
      else 
        c.chooseBranch("Attend a church fête on the south bank of the River")
    ),
    "Court and Cell: the Constables" -> Conditional(c => c.qualities("Connected: Constables") >= 15 || c.items("Rostygold") >= 10, implicit c =>
      if (c.qualities("Connected: Constables") >= 15) {
        gear.watchful()
        c.chooseBranch("Attend a class given by the Implacable Detective")
      } else {
        c.chooseBranch("A small donation")
      }
    ),
    "The Demi-Monde: Bohemians" -> Conditional(_.qualities("Connected: Bohemian") >= 3, _.chooseBranch("Take tea with a Reclusive Novelist")),
    "Bandages and Dust: The Tomb-Colonies" -> Conditional(_.qualities("Connected: The Tomb-Colonies") >= 3, { implicit c => gear.dangerous(); c.chooseBranch("Spar with a Black Ribbon Duellist") }),
    "Whispers from the Surface: The Great Game" -> Conditional(c => (c.qualities("Connected: The Great Game") >= 10 && c.watchful <= 70) || c.qualities("Connected: The Great Game") >= 20, c =>
      if (c.watchful > 70)
        c.chooseBranch("Learn more at the carnival")
      else 
        c.chooseBranch("Get some work done at Clathermont's tattoo parlour")
    ),
    "The Roof-Tops: Urchins" -> Conditional(c => (c.qualities("Connected: Urchins") >= 3 && c.shadowy < 71) || c.items("Glim") >= 20 || c.items("Lucky Weasel") >= 1, c => 
      if (c.shadowy > 70 && c.items("Glim") >= 20)
        c.chooseBranch("Out you go, longshanks")
      else if (c.shadowy < 71 && c.qualities("Connected: Urchins") >= 3)
        c.chooseBranch("Run the rooftops with the urchins")
      else
        c.chooseBranch("In the shadow of All Christs Spire")
    ),
    "Burning Shadows: the Devils of London" -> Playable(c => 
      if (c.watchful > 70 && c.qualities("Connected: Hell") > 2) {
        c.chooseBranch("Speak with a senior deviless")
      } else {
        c.chooseBranch("Attend a lecture at the Brass Embassy")
      }
    ),
    "Gunpowder and Zeal: the Revolutionaries" -> Playable(_.chooseBranch("Taking a walk down gin lane")),
    
    //Connection vs connection - +15cp net. worth the action?
    "The Devil and the Child" -> Playable(c =>
      if (c.qualities("Connected: Hell") <= c.qualities("Connected: Urchins"))
        c.chooseBranch("Take the Devil's side")
      else
        c.chooseBranch("Convince the urchin to keep his soul")
    ),
    "Going gentle" -> Playable(implicit c => 	//there's another option here, Box-related, for later
      if (c.qualities("Connected: The Tomb-Colonies") <= c.qualities("Connected: Society")) {
        gear.dangerous()
        c.chooseBranch("Break him out!") 
      } else {
        c.chooseBranch("Discreetly inform the family about the baronet's communication")
      }
    ),
    
    //Counting the Days
    "The Awful Temptation of Money" -> Trivial,
    "Graffiti with a sting" -> Conditional(_.qualities("Counting the Days") >= 10, _.chooseBranch("Ask someone else what they saw")),
    
    //Misc playbles
    "Pass the Cat: a wriggling delivery" -> Conditional(_.qualities("Scandal") > 0, _.chooseBranch("An elaborate strategy")),
    "Wanted: Reminders of Brighter Days" -> Conditional(_.items("Incendiary Gossip") >= 25, _.chooseBranch("The tiniest of classified advertisements")),
    "Mr Wines is holding a sale!" -> Conditional(_.items("Romantic Notion") >= 80, _.chooseBranch("A discount for purchase in bulk")),
    "Lies below the Palace" -> Conditional(_.qualities("Nightmares") < 7, _.chooseBranch()), //okish Watchful/rumour grind- +18 proscribed. reconsider later
    "The Ambassador's ball" -> Conditional(c => c.persuasive > 80 && c.persuasive < 119, _.chooseBranch("Making a point of not making a point")),
    "A night at the carnival" -> Playable(implicit c => { gear.dangerous(); c.chooseBranch("There's always something") }),
    "A consideration for services rendered" -> Conditional(_.items("Soul") > 0, _.chooseBranch()),
    "A parliament of bats" -> Playable(_.chooseBranch("Release a bat into the cloud")),
    "The Ways of the Flit" -> Conditional(_.shadowy >= 70, _.chooseBranch("An old street sign")),
    "The Correspondence Savages Your Dreams" -> Conditional(_.qualities("Nightmares") < 7, implicit c => { gear.watchful(); c.chooseBranch("Perhaps you can remember something useful") }),
    
    //Misc to explicitly keep 
    "What will you do with your Partisan Messenger Tortoise?" -> Unplayable,
    "Help the Sardonic Music-Hall Singer" -> Unplayable,
    "A presumptuous little opportunity" -> Unplayable
  ) withDefaultValue Unplayable

  def london = new Opportunist(playlist, blacklist)
}
