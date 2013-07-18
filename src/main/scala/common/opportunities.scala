package common
import london._

object opportunities {  
  //auto-discard these, unless they are playable
  private val alwaysUseless = Set(
    "Help a spy distract an inconvenient tail",             //best reward: 36 jade
    "A meeting of cats",                                    //best reward: 24 clues
    "The notable citizen",                                  //best reward: 83 whispered secrets and it can't be made scandal-safe
    "Shroom-hopping: a quaint sport of the lower classes",  //best reward: 18 '82
    "What profit?",                                         //i don't want to sell my soul! at least not cheaply
    "The tenor's minder",                                   //reward low, dangerous range too small
    "A runaway horse!",                                     //reward low, dangerous range too small
    "Romance and practicality",                             //best reward: 36 whispered secrets
    "Stark villainy",                                       //best reward: like 30 rostygold
    "The marriage of inconvenience",                        //best reward: 50 secrets
    "Rob a Brass Embassy courier",                          //15 proscribed material
    "The Ways of the University",
    "The Ways of the Forgotten Quarter",
    "The Ways of Wolfstack Docks",
    "The Ways of the Shuttered Palace"
  )
  
  private val sometimesUseless = Set(
    "Pass the Cat: a wriggling delivery", 			//the only benefit is -scandal, which i might not need
    "Wanted: Reminders of Brighter Days", 			//it's not worth keeping around on the off-chance of saving an action
    "The Ambassador's ball",              			//might not be in the range to play it - only gives a confident smile
    "Whispers from the Surface: The Great Game",	//can't use it to grind
    "A consideration for services rendered",      	//not worth grinding souls for
    "Graffiti with a sting",						//only useful when Counting the Days
    "The Ways of the Flit"							//street signs are valuable, but it's difficult
  )
  
  //auto-play these if conditions are met
  private val lodgingCards = Map(
    "The Sleepless Tower" -> Playable(_.chooseBranch("Spores and fangs")),
    "The Tower of Sparrows" -> Playable(_.chooseBranch("Settle down to a game of cards")),
    "The Tower of Sleeping Giants" -> Playable(c =>
      if (c.items("An Infernal Contract") < 100)
        c.chooseBranch("The owner")
      else 
        c.chooseBranch("Examine the stock") 
    ),
    "The Heron Tower" -> Playable(implicit c => {
      gear.dangerous()
      c.chooseBranch("Peril and pyjamas")
    }),
    "The Lofty Tower" -> Unplayable,
    "The Western Tower" -> Unplayable,
    "The Tower of Sun and Moon" -> Unplayable
  )
  
  private val connectionCards = Map(
    "Altars and alms-houses: the Church" -> Conditional(c => c.qualities("Connected: The Church") >= 20 || c.items("Rostygold") >= 10, c =>
      if (c.qualities("Connected: The Church") >= 20)
        c.chooseBranch("Attend a private lecture given by the Bishop of Southwark")
      else 
        c.chooseBranch("Attend a church fête on the south bank of the River")
    ),
    "Court and Cell: the Constables" -> Conditional(c => c.qualities("Connected: Constables") >= 15 || c.items("Rostygold") >= 10, implicit c =>
      if (c.qualities("Connected: Constables") >= 15) {
        gear.watchful()
        c.refreshBranches()
        c.chooseBranch("Attend a class given by the Implacable Detective")
      } else {
        c.chooseBranch("A small donation")
      }
    ),
    "By the River's Side: the Docks" -> Conditional(c => c.qualities("Connected: The Docks") >= 3 || c.items("Rostygold") >= 10, implicit c =>
      if (c.qualities("Connected: The Docks") >= 3) {
        gear.dangerous()
        c.refreshBranches()
        c.chooseBranch("Fencing lessons with a Dashing Captain")
      } else {
        c.chooseBranch("Buy a round at the Rusty Tramp")
      }
    ),
    "Burning Shadows: the Devils of London" -> Playable(implicit c => 
      if (c.qualities("Connected: Hell") >= 3) {
        gear.watchful()
        c.refreshBranches()
        c.chooseBranch("Speak with a senior deviless")
      } else {
        c.chooseBranch("Attend a lecture at the Brass Embassy")
      }
    ),
    "The Demi-Monde: Bohemians" -> Conditional(c => c.qualities("Connected: Bohemian") >= 3 || c.items("Greyfields 1882") >= 2, c => 
      if (c.qualities("Connected: Bohemian") >= 3)
        c.chooseBranch("Take tea with a Reclusive Novelist")
      else
        c.chooseBranch("Buy drinks for writers")
    ),
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
    "Gunpowder and Zeal: the Revolutionaries" -> Playable(_.chooseBranch("Taking a walk down gin lane"))
  )
  
  private val conflictCards = Map(
    "The Devil and the Child" -> Playable(c =>
      if (c.qualities("Connected: Hell") <= c.qualities("Connected: Urchins"))
        c.chooseBranch("Take the Devil's side")
      else
        c.chooseBranch("Convince the urchin to keep his soul")
    ),
    /*"Going gentle" -> Playable(implicit c =>
      if (c.qualities("Connected: The Tomb-Colonies") <= c.qualities("Connected: Society")) {
        gear.dangerous()
        c.chooseBranch("Break him out!") 
      } else {
        c.chooseBranch("Discreetly inform the family about the baronet's communication")
      }
    ),*/
    "A contact in the Great Game has a tale for you" -> Playable(c =>
      if (c.qualities("Connected: The Great Game") <= c.qualities("Connected: The Church")) {
        c.chooseBranch("Make it clear that no-one leaves the Game") 
      } else {
        c.chooseBranch("Everyone deserves a second chance")
      }
    ),
    "Youthful high spirits" -> Playable(c =>
      if (c.qualities("Connected: The Docks") <= c.qualities("Connected: Urchins")) {
        c.chooseBranch("Sabotage the cannon") 
      } else {
        c.chooseBranch("Convince the residents to pay their protection fees to the urchins instead")
      }
    )
  )
  
  private val londonCards = Map(
    "Recapturing a prison escapee" -> Trivial,
    "A parliament of bats" -> Playable(_.chooseBranch("Release a bat into the cloud")),
    "A night at the carnival" -> Playable(implicit c => { gear.dangerous(); c.chooseBranch("There's always something") }),
    "Pass the Cat: a wriggling delivery" -> Conditional(_.qualities("Scandal") > 0, _.chooseBranch("An elaborate strategy")),
    "Wanted: Reminders of Brighter Days" -> Conditional(_.items("Incendiary Gossip") >= 25, _.chooseBranch("The tiniest of classified advertisements")),
    "Mr Wines is holding a sale!" -> Conditional(_.items("Romantic Notion") >= 80, _.chooseBranch("A discount for purchase in bulk")),
    "Lies below the Palace" -> Conditional(_.qualities("Nightmares") < 7, _.chooseBranch()), //okish Watchful/rumour grind- +18 proscribed. reconsider later
    "The Ambassador's ball" -> Conditional(c => c.persuasive > 80 && c.persuasive < 119, _.chooseBranch("Making a point of not making a point")),
    "A consideration for services rendered" -> Conditional(_.items("Soul") > 0, _.chooseBranch()),
    "The Ways of the Flit" -> Conditional(_.shadowy >= 70, _.chooseBranch("An old street sign")),
    "The Correspondence Savages Your Dreams" -> Conditional(_.qualities("Nightmares") < 7, implicit c => { gear.watchful(); c.chooseBranch("Perhaps you can remember something useful") }),
    "What will you do with your Partisan Messenger Tortoise?" -> Unplayable,
    "Help the Sardonic Music-Hall Singer" -> Unplayable,
    "A presumptuous little opportunity" -> Unplayable
  )
  
  private val countingTheDays = Map(
    "The Awful Temptation of Money" -> Trivial,
    "Graffiti with a sting" -> Conditional(_.qualities("Counting the Days") >= 10, _.chooseBranch("Ask someone else what they saw"))
  )
  
  private val affairOfTheBox = Map(
    "Going gentle" -> Unplayable
  )

  def london = new Opportunist((lodgingCards ++ connectionCards ++ conflictCards ++ londonCards) withDefaultValue Unplayable, 
                               (alwaysUseless ++ sometimesUseless))
}
