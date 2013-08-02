package david
import london._
import common._

object opportunities {  
  //auto-discard these, unless they are playable
  private val alwaysUseless = Set(
    "Help a spy distract an inconvenient tail",             //36 jade
    "A meeting of cats",                                    //24 clues, never 100%
    "The notable citizen",                                  //83 whispered secrets, but i can grind that easily and it can't be made scandal-safe
    "Shroom-hopping: a quaint sport of the lower classes",  //18 '82
    "A Sporting Sort",                                      //pure gamble
    "What profit?",                                         //i don't want to sell my soul! at least not cheaply
    "The tenor's minder",                                   //reward low, dangerous range too small
    "A runaway horse!",                                     //reward low, dangerous range too small
    "Romance and practicality",                             //36 whispered secrets
    "Stark villainy",                                       //36 rostygold
    "The marriage of inconvenience",                        //50 whispered secrets
    "Rob a Brass Embassy courier",                          //15 proscribed material
    "Academic discipline",                                  //0.6E '79,
    "A chance encounter with an old friend...",             //quirk gains cap at 6
    "A libraryette for Mr Pages",                           //req: too much to grind for, for now
    "Infiltrate a gentlemen's club",                        //0.6E stuff, connections but can't be menace-free
    "A day in the garden",                                  //0.6E secrets
    "The ever-present and invisible servantry",             //<0.4E stuff
    "A limping figure in a top hat beckons",                //amber for too much connection
    "Work the carnival",									                  //12 4th relics
    "The young buck",                                       //67 silk or 84 rostygold, never 100%
    "The marvellous contrivance",                           //69 candles or 84 jade, never 100%
    "Ambushed by pirates!",                                 //72 glim, never 100%
    "An evening's zailing",                                 //bad conn card - always +1 to one, -5 to the other
    "Rob a Brass Embassy courier",                          //15 proscribed, never 100%
    "Undertakings",                                         //too-narrow a watchful range
    "Recapturing a prison escapee",                         //too-narrow a watchful range
    "Moonish water",                                        //net +.8E secrets, narrow dangerous range
    "Race across the river",                                //84 glim
    "The Noted Orchid-Grower consults",                     //requires fate for small payoff
    "Medical Emergency",                                    //<50 secrets
    "A simple job from a devil",                            //72 brass
    "As silent as the grave?",                              //0.75E misc goods
    "Property most intellectual",                           //<1E correspondence
    "Lies below the Palace",                                //18 proscribed (<0.8E)
    "Trailing the Affluent Photographer",                   //i have better gear than this gives now
    "Robbing the Ambassador's ball",                        //34 clues or 84 amber
    "The Dean's distress",             
    "The Ways of the University",
    "The Ways of the Forgotten Quarter",
    "The Ways of Wolfstack Docks",
    "The Ways of the Shuttered Palace"
  )
  
  private val sometimesUseless = Set(
    "The simple joys of villainy",              //crap reward, i just want the shadowy
    "Pass the Cat: a wriggling delivery", 			//the only benefit is -scandal, which i might not need
    "Wanted: Reminders of Brighter Days", 			//it's not worth keeping around on the off-chance of saving an action
    "The Ambassador's ball",              			//might not be in the range to play it - only gives a confident smile
    "A consideration for services rendered",    //not worth grinding souls for
    "Graffiti with a sting",	          				//only useful when Counting the Days
    "A night on the tiles"                      //doesn't way well without the wine
  )
  
  //auto-play these if conditions are met
  private val lodgingCards = Map(
    "The Tower of Sparrows" -> Playable(_.chooseBranch("Settle down to a game of cards")),                                                            //persuasive t2
    "The Sleepless Tower" -> Playable(_.chooseBranch("Spores and fangs")),                                                                            //dangerous t2 
    "The Tower of Knives" -> Playable(_.chooseBranch("Rough camaraderie")),                                                                           //shadowy t2
    "The Tower of Sleeping Giants" -> Playable(c => c.chooseBranch(if (c.items("An Infernal Contract") < 100) "The owner" else "Examine the stock")), //watchful t2
    
    "The Tower of Eyes" -> Playable(_.chooseBranch("Do a little promenading yourself")),                                                              //persuasive t2.5
    "The Heron Tower" -> Playable(_.chooseBranch("Hunt down a huge lizard")),                                                                         //dangerous t2.5
    "The Listing Tower" -> Unplayable,														                                                                                    //dangerous t2.5
    "The Windward Tower" -> Unplayable,														                                                                                    //shadowy t2.5
    "The High Castle" -> Playable(implicit c => {											                                                                                //shadowy t2.5
      if (c.items("Greyfields 1882") < 1000)
        c.chooseBranch("Talk to a friend of a friend")
      else {
        gear.shadowy()  //straightforward at 67
        c.chooseBranch("A stroll with a sack")
      }
    }),
    
    "The Lofty Tower" -> Unplayable,														                                                                                      //persuasive t3
    "The Western Tower" -> Unplayable,														                                                                                    //watchful t3
    "The Tower of Sun and Moon" -> Unplayable												                                                                                  //watchful t3
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
        c.chooseBranch("Attend a class given by the Implacable Detective")
      } else {
        c.chooseBranch("A small donation")
      }
    ),
    "By the River's Side: the Docks" -> Conditional(c => c.qualities("Connected: The Docks") >= 3 || c.items("Rostygold") >= 10, implicit c =>
      if (c.qualities("Connected: The Docks") >= 3) {
        gear.dangerous()
        c.chooseBranch("Fencing lessons with a Dashing Captain")
      } else {
        c.chooseBranch("Buy a round at the Rusty Tramp")
      }
    ),
    "Burning Shadows: the Devils of London" -> Playable(implicit c => 
      if (c.qualities("Connected: Hell") >= 3) {
        gear.watchful()
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
    "Bandages and Dust: The Tomb-Colonies" -> Conditional(_.qualities("Connected: The Tomb-Colonies") >= 3, implicit c => {
      gear.dangerous()
      c.chooseBranch("Spar with a Black Ribbon Duellist") 
    }),
    "Whispers from the Surface: The Great Game" -> Conditional(_.qualities("Connected: The Great Game") >= 20, implicit c => {
      gear.watchful()
      c.chooseBranch("Learn more at the carnival")
    }),
    "The Roof-Tops: Urchins" -> Conditional(c => c.items("Glim") >= 20 || c.items("Lucky Weasel") >= 1, implicit c => {
      gear.shadowy()
      if (c.items("Glim") >= 20 && c.qualities("Connected: Urchins") >= 10)
        c.chooseBranch("Out you go, longshanks")
      else
        c.chooseBranch("In the shadow of All Christs Spire")
    }),
    "Park and Palace: Society" -> Playable(c => 
      if (c.qualities("Connected: Society") >= 40)
        c.chooseBranch("Take port with the Veteran Privy Counsellor") //+10cp persuasive, -400cp connected
      else
        c.chooseBranch("An invitation to dinner")   //+connected, -wounds
    ),
    "Gunpowder and Zeal: the Revolutionaries" -> Playable(_.chooseBranch("Taking a walk down gin lane"))  //counting the days
    //"The Alleys of London: the Criminals" -> Conditional(_.qualities("Connected: Criminals"))
  )
  
  private val conflictCards = Map(
    "The Devil and the Child" -> Playable(c =>
      if (c.qualities("Connected: Hell") <= c.qualities("Connected: Urchins"))
        c.chooseBranch("Take the Devil's side")
      else
        c.chooseBranch("Convince the urchin to keep his soul")
    ),
    "Going gentle" -> Playable(implicit c =>
      if (c.qualities("Connected: The Tomb-Colonies") <= c.qualities("Connected: Society")) {
        gear.dangerous()
        c.chooseBranch("Break him out!") 
      } else {
        c.chooseBranch("Discreetly inform the family about the baronet's communication")
      }
    ),
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
    ),
    "Brimstone or frankincense?" -> Playable(c =>
      if (c.qualities("Connected: Hell") <= c.qualities("Connected: The Church")) {
        c.chooseBranch("Firecrackers in the thurible!") 
      } else {
        c.chooseBranch("Alert the vicar")
      }
    ),
    "The kaleidoscopic church" -> Playable(c =>
      if (c.qualities("Connected: Bohemian") <= c.qualities("Connected: The Church")) {
        c.chooseBranch("Secure the artists’ work") 
      } else {
        c.chooseBranch("Make it clear the Bohemians aren’t welcome any more")
      }
    ),
    "A familiar face by the school railings" -> Playable(c =>
      if (c.qualities("Connected: The Orient") <= c.qualities("Connected: Urchins")) {
        c.chooseBranch("Advise the girl to return to the Widow") 
      } else {
        c.chooseBranch("Arrange for the girl to return to the urchin-gangs")
      }
    ),
    "Amber in the well" -> Playable(c =>
      if (c.qualities("Connected: Revolutionaries") <= c.qualities("Connected: Rubbery Men")) {
        c.chooseBranch("Convince the Rubbery Men to move on") 
      } else {
        c.chooseBranch("Convince the revolutionaries to find somewhere else")
      }
    ),
    "They all look the same to me" -> Playable(c =>
      if (c.qualities("Connected: Rubbery Men") <= c.qualities("Connected: Constables")) {
        c.chooseBranch("Finger a scapegoat.") 
      } else {
        c.chooseBranch("Finger the guilty party")
      }
    )
  )
  
  private val londonCards = Map(
    "A rather decadent evening" -> Trivial,
    "A parliament of bats" -> Playable(_.chooseBranch("Release a bat into the cloud")),
    "The Northbound Parliamentarian" -> Playable(_.chooseBranch("Advise prudence in her latest bill")),
    "A night at the carnival" -> Playable(implicit c => { gear.dangerous(); c.chooseBranch("There's always something") }),
    "The Parthenaeum" -> Playable(implicit c => { gear.dangerous(); c.chooseBranch("Expelling undesirables") }),  //conn:soc and 0.6E wines 
    "The Ways of the Flit" -> Playable(implicit c => { gear.shadowy(); c.chooseBranch("An old street sign") }), //2.5E
    "One's public" -> Playable(implicit c => { gear.persuasive(); c.chooseBranch("Put on a fine show for them") }), //>2E of stuff!
    "His Young Lordship seized by tentacles" -> Playable(_.chooseBranch("Sell snacks to the crowd")),
    "Minding the detective" -> Playable(implicit c => { gear.dangerous(); c.chooseBranch("The case of the frenzied mandrake") }),  //business card (and 60 rostygold)
    "Rat Melancholy" -> Playable(_.chooseBranch("Listen to her story")),  //50 cryptic clues, rat sympathy
    "A past benefactor" -> Playable(_.chooseBranch("And what of the secrets of Hell?")),  //sudden insight
    "What's in the sack, Jack?" -> Conditional(_.qualities("Wounds") < 7, implicit c=> { gear.dangerous(); c.chooseBranch()}), //18 proscribed, 100% at 110
    "The simple joys of villainy" -> Conditional(_.qualities("Suspicion") < 7, implicit c => { gear.shadowy(); c.chooseBranch("Hire some help and strip the place bare") }), //only 36 beeswax, but +shadowy
    "The Eye and the Camera" -> Conditional(_.qualities("Suspicion") < 7, implicit c => { gear.watchful(); c.chooseBranch("Gather evidence... with a camera!") }), //min. 0.8E worth of luminosity
    "Rob a library at the University" -> Conditional(_.qualities("Suspicion") < 7, implicit c => { gear.shadowy(); c.chooseBranch() }), //conn: rev and 15 proscribed
    "Pass the Cat: a wriggling delivery" -> Conditional(_.qualities("Scandal") > 0, _.chooseBranch("An elaborate strategy")),
    "The Fallen Angel" -> Conditional(_.qualities("Wounds") < 7, implicit c => {gear.dangerous(); c.chooseBranch("Tackle the verger")}),	//conn: church and a second chance
    "Wanted: Reminders of Brighter Days" -> Conditional(_.items("Incendiary Gossip") >= 25, _.chooseBranch("The tiniest of classified advertisements")),
    "Mr Wines is holding a sale!" -> Conditional(_.items("Romantic Notion") >= 80, _.chooseBranch("A discount for purchase in bulk")),
    "The Ambassador's ball" -> Conditional(c => c.persuasive > 80 && c.persuasive < 119, _.chooseBranch("Making a point of not making a point")),
    "A consideration for services rendered" -> Conditional(_.items("Soul") > 0, _.chooseBranch()),
    "The Correspondence Savages Your Dreams" -> Conditional(_.qualities("Nightmares") < 7, implicit c => { gear.watchful(); c.chooseBranch("Perhaps you can remember something useful") }),
    "Consulting detective required for government work" -> Conditional(_.qualities("Nightmares") < 7, implicit c => { gear.watchful(); c.chooseBranch("Accept the case, but...") }),  //21 proscribed and +subtle
    "A night on the tiles" -> Conditional(_.items("Greyfields 1868 First Sporing") > 0, _.chooseBranch("A bottle of the '68")), //1E of influence 
    "What will you do with your Partisan Messenger Tortoise?" -> Unplayable,
    "Help the Sardonic Music-Hall Singer" -> Unplayable, //investigate the other-acquaintance options, otherwise Playable with persuasive-alone option
    "Ask the Sardonic Music-Hall Singer to help you" -> Unplayable, //not 100% but all options >1 E - make it Playable with higher stats
    "A presumptuous little opportunity" -> Unplayable,
    "Tea with the Inspector" -> Unplayable //useful if on a case i think? 
  )
  
  def deprioritise[T <% Ordered[T]](xs: Set[T], worst: T) = xs.toSeq.sortBy(b => -Math.abs(b.compareTo(worst))).head
  
  private val countingTheDays = Map(
    "The Awful Temptation of Money" -> Trivial,
    "Graffiti with a sting" -> Conditional(_.qualities("Counting the Days") >= 10, _.chooseBranch("Ask someone else what they saw")),
    "The Law's Long Arm" -> Playable(c => {
      c.chooseBranch(deprioritise(c.branches, "Official incompetence"))
    }),
    "A Moment's Peace" -> Playable(c => {
      c.chooseBranch(deprioritise(c.branches - "Follow a light into the trees", "Relax and enjoy"))
    }),
    "A Restorative" -> Playable(c => {
      c.chooseBranch(deprioritise(c.branches - "A sumptuous repast!", "Scraps from the table"))
    }),
    "An afternoon of good deeds?" -> Playable(c => {
      c.chooseBranch(deprioritise(c.branches - "A plaster saint!" - "An afternoon of mischief!", "Quite a moral afternoon."))
    })
  )
  
  private val affairOfTheBox = Map(
    "Going gentle" -> Unplayable
  )

  val london = new Opportunist((lodgingCards ++ connectionCards ++ conflictCards ++ londonCards ++ countingTheDays) withDefaultValue Unplayable, 
                               (alwaysUseless ++ sometimesUseless))
}
