package david.london
import api._
import common._
import david.gear

object opportunities extends Opportunist(
  Map(
    /*****************/
    /* LODGING CARDS */
    /*****************/
    "The Tower of Sparrows" -> Playable(_.chooseBranch("Settle down to a game of cards")),                                                            //persuasive t2
    "The Sleepless Tower" -> Playable(_.chooseBranch("Spores and fangs")),                                                                            //dangerous t2 
    "The Tower of Knives" -> Playable(_.chooseBranch("Rough camaraderie")),                                                                           //shadowy t2
    "The Tower of Sleeping Giants" -> Playable(c => c.chooseBranch(if (c.items("An Infernal Contract") < 100) "The owner" else "Examine the stock")), //watchful t2
    "The Tower of Eyes" -> Conditional(_.qualities("Connected: Summerset") == 0, _.chooseBranch("Do a little promenading yourself")),                 //persuasive t2.5
    "The Heron Tower" -> Playable(_.chooseBranch("Hunt down a huge lizard")),                                                                         //dangerous t2.5
    "The Listing Tower" -> Unplayable, //I think I'm too Dangerous to ever get this?                                                                  //dangerous t2.5
    "The Windward Tower" -> Playable(_.chooseBranch("The cautious contact")),                                                                         //shadowy t2.5
    "The High Castle" -> Playable(_.chooseBranch("A stroll with a sack")),                                                                            //shadowy t2.5
    "The Lofty Tower" -> Playable(_.chooseBranch("Engage in commerce")),                                                                              //persuasive t3
    "The Western Tower" -> Unplayable,                                                                                                                //watchful t3
    "The Tower of Sun and Moon" -> Unplayable,                                                                                                        //watchful t3
    
    /********************/
    /* CONNECTION CARDS */
    /********************/
    "Altars and alms-houses: the Church" -> Conditional(c => c.qualities("Connected: The Church") >= 30 || c.items("Rostygold") >= 10, c =>
      if (c.qualities("Connected: The Church") >= 30)
        c.chooseBranch("Attend a private lecture given by the Bishop of Southwark")
      else 
        c.chooseBranch("Attend a church fête on the south bank of the River")
    ),
    "Court and Cell: the Constables" -> Conditional(c => c.qualities("Connected: Constables") >= 30 || c.items("Rostygold") >= 10, implicit c =>
      if (c.qualities("Connected: Constables") >= 30) {
        gear.watchful()
        c.chooseBranch("Attend a class given by the Implacable Detective")
      } else {
        c.chooseBranch("A small donation")
      }
    ),
    "By the River's Side: the Docks" -> Conditional(c => c.qualities("Connected: The Docks") >= 30 || c.items("Rostygold") >= 10, implicit c =>
      if (c.qualities("Connected: The Docks") >= 30) {
        gear.dangerous()
        c.chooseBranch("Fencing lessons with a Dashing Captain")
      } else {
        c.chooseBranch("Buy a round at the Rusty Tramp")
      }
    ),
    "Burning Shadows: the Devils of London" -> Playable(implicit c => 
      if (c.qualities("Connected: Hell") >= 30) {
        gear.watchful()
        c.chooseBranch("Speak with a senior deviless")
      } else {
        c.chooseBranch("Attend a lecture at the Brass Embassy")
      }
    ),
    "The Demi-Monde: Bohemians" -> Conditional(c => c.qualities("Connected: Bohemian") >= 60 || c.items("Greyfields 1882") >= 2, c => 
      if (c.qualities("Connected: Bohemian") >= 60)
        c.chooseBranch("Take tea with a Reclusive Novelist")
      else
        c.chooseBranch("Buy drinks for writers")
    ),
    "Bandages and Dust: The Tomb-Colonies" -> Conditional(_.qualities("Connected: The Tomb-Colonies") >= 30, implicit c => {
      c.chooseBranch("Spar with a Black Ribbon Duellist") 
    }),
    "Whispers from the Surface: The Great Game" -> Conditional(_.qualities("Connected: The Great Game") >= 30, implicit c => {
      gear.watchful()
      c.chooseBranch("Learn more at the carnival")
    }),
    "The Roof-Tops: Urchins" -> Conditional(c => (c.items("Glim") >= 20 && c.qualities("Connected: Urchins") >= 30) || c.items("Lucky Weasel") >= 1, implicit c => {
      gear.shadowy()
      if (c.items("Glim") >= 20 && c.qualities("Connected: Urchins") >= 30)
        c.chooseBranch("Out you go, longshanks")
      else
        c.chooseBranch("In the shadow of All Christs Spire")
    }),
    "Park and Palace: Society" -> Playable(c => 
      if (c.qualities("Connected: Society") >= 60)
        c.chooseBranch("Take port with the Veteran Privy Counsellor") //+10cp persuasive, -400cp connected
      else
        c.chooseBranch("An invitation to dinner")   //+connected, -wounds
    ),
    "The Alleys of London: the Criminals" -> Conditional(_.qualities("Connected: Criminals") >= 30, implicit c => {
      gear.shadowy()
      c.chooseBranch("Consult with a master thief")
    }), 
    "Gunpowder and Zeal: the Revolutionaries" -> Playable(_.chooseBranch("Taking a walk down gin lane")), //counting the days
    
    
    
    /******************/
    /* CONFLICT CARDS */
    /******************/
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
        c.chooseBranch("Convince the Revolutionaries to find somewhere else")
      }
    ),
    "They all look the same to me" -> Playable(c =>
      if (c.qualities("Connected: Rubbery Men") <= c.qualities("Connected: Constables")) {
        c.chooseBranch("Finger a scapegoat.") 
      } else {
        c.chooseBranch("Finger the guilty party")
      }
    ),
    "A misfortune at the Carnival" -> Playable(c =>
      if (c.qualities("Connected: Rubbery Men") <= c.qualities("Connected: The Tomb-Colonies")) {
        c.chooseBranch("Save the Rubbery Man") 
      } else {
        c.chooseBranch("Save the Tomb-Colonist!")
      }
    ),
    "The Tomb-colonist and the Footpad" -> Conditional(_.items("Intriguing Gossip") >= 1, c =>
      if (c.qualities("Connected: Criminals") <= c.qualities("Connected: The Tomb-Colonies"))
        c.chooseBranch("Accept the job")
      else
        c.chooseBranch("Double-cross him")
    ),
    "A tavern dust-up" -> Conditional(_.items("Cryptic Clue") >= 10, c => {
      val faction = Set("Connected: The Constables", "Connected: The Church", "Connected: The Docks").min(Ordering.by(c.qualities))
      c.chooseBranch("Intervene to help the " + Map(
        "Connected: The Constables" -> "Constable",
        "Connected: The Church" -> "cleric",
        "Connected: The Docks" -> "docker"
      )(faction))
    }),
    "Valuable Secrets" -> Conditional(_.items("Whispered Secret") >= 15, c => {
      val faction = Set("Connected: Hell", "Connected: The Great Game", "Connected: Bohemian").min(Ordering.by(c.qualities))
      c.chooseBranch(Map(
        "Connected: Hell" -> "Sell the information to the Embassy",
        "Connected: The Great Game" -> "Find a buyer for the information",
        "Connected: Bohemian" -> "Warn the artist"
      )(faction))
    }),
    "A scuffle on the street" -> Playable(c => {
      val faction = Set("Connected: Society", "Connected: Revolutionaries", "Connected: Urchins").min(Ordering.by(c.qualities))
      c.chooseBranch(Map(
        "Connected: Society" -> "Stand up for the Lady",
        "Connected: Revolutionaries" -> "Take the Revolutionary's side",
        "Connected: Urchins" -> "Assist the Urchin"
      )(faction))
    }),
    
    
    
    /*********************/
    /* COUNTING THE DAYS */
    /*********************/
    "The Awful Temptation of Money" -> Trivial,
    "Graffiti with a sting" -> Conditional(_.qualities("Counting the Days") >= 10, _.chooseBranch("Ask someone else what they saw")),
    "An unusual wager" -> Conditional(_.qualities("Counting the Days") < 10, _.chooseBranch("Look at those coins")),
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
    }),
    
    
    /* ACQUAINTANCE CARDS */
    "Ask the Sardonic Music-Hall Singer to help you" -> Playable(implicit c => {
      gear.persuasive()
      c.chooseBranch("An invitation to a rather exclusive soirée")  //1.2E of gossip, or rare Aeolian Scream
    }),
    
    
    /***************/
    /* OTHER CARDS */
    /***************/
    "A rather decadent evening" -> Trivial,
    "A day without laudanum" -> Autofire,
    "A parliament of bats" -> Playable(_.chooseBranch("Release a bat into the cloud")),
    "Investigate the Topsy King's court" -> Playable(_.chooseBranch("Spy on the dealings with revolutionaries")), //conn:const and 64 rostygold
    "The Geology of Winewound" -> Playable(implicit c => { gear.watchful(); c.chooseBranch("Go as far as you can") }), //130E of relics, rare for visions
    "The Parthenaeum" -> Playable(implicit c => { gear.dangerous(); c.chooseBranch("Roust out an interloper") }),  //conn:soc and 0.6E wines 
    "One's public" -> Playable(implicit c => { gear.persuasive(); c.chooseBranch("Put on a fine show for them") }), //>2E of stuff!
    "A riot in Spite!" -> Playable(_.chooseBranch("Wade in with fists flying")), //connections
    "The Cities that Fell" -> Playable(implicit c => { gear.watchful(); c.chooseBranch("Ancient stories") }), //with POSI, 3 visions
    "Devices and desires" -> Playable(implicit c => { gear.watchful(); c.chooseBranch("The trade in clocks") }), //with POSI, 10 notions
    "His Young Lordship seized by tentacles" -> Playable(_.chooseBranch("Sell snacks to the crowd")),
    "Minding the detective" -> Playable(implicit c => { gear.dangerous(); c.chooseBranch("The case of the frenzied mandrake") }),  //business card (and 60 rostygold)
    "Rat Melancholy" -> Playable(_.chooseBranch("Let her grieve in dignified silence")),  //50 '82, 3cp rat sympathy
    "A past benefactor" -> Playable(_.chooseBranch("And what of the secrets of Hell?")),  //sudden insight
    "The Paronomastic Newshound" -> Playable(_.chooseBranch("Talk to him about the Tomb-Colonies")),  //
    "The little people" -> Playable(_.chooseBranch("Do your best for him")),  //80 pearls, 5 conn:crim, rare=bribr
    "Riding your Velocipede" -> Playable(_.chooseBranch("The velocipede courier")),
    "The tomb-colonist's dogs" -> Playable(_.chooseBranch("Could you look after them for a day?")), //61 candle, +10cp colonies
    "Below the Neath" -> Playable(_.chooseBranch("Go and see what else you can find")), //70 souls
    "Cheesemonger no more" -> Playable(c => {
      if (c.qualities("Melancholy") < 9) 
        c.chooseBranch("You regret what happened")
      else if (c.qualities("Steadfast") < 9) 
        c.chooseBranch("You did the right thing")
      else if (c.qualities("Ruthless") < 9) 
        c.chooseBranch("It's all in the game")
      else {
        c.perhapsNot()
        c.discardOpportunity("Cheesemonger no more")
      }
    }), //+1 HEL and quirks up to 9
    "A Day with God's Editors" -> Playable(c => {
      if (c.qualities("Scandal") > 0 && c.qualities("Nightmares") > 0) 
        c.chooseBranch("Work diligently") //-1cp of each and 5cp conn:church
      else 
        c.chooseBranch("Examine the latest revisions")
    }), //+1 HEL and quirks up to 9
    "A relaxed day at the Club" -> Playable(c =>
      if (c.qualities("Suspicion") > 1) {
        c.chooseBranch("Have a little word with the Chief Constable")
      } else {
        c.chooseBranch("Fall asleep in front of the fire")
      }
    ),
    "What's in the sack, Jack?" -> Conditional(_.qualities("Wounds") < 7, implicit c=> { gear.dangerous(); c.chooseBranch()}), //18 proscribed, 100% at 110
    "Rob a library at the University" -> Conditional(_.qualities("Suspicion") < 7, implicit c => { gear.shadowy(); c.chooseBranch() }), //conn: rev and 15 proscribed
    "Pass the Cat: a wriggling delivery" -> Conditional(_.qualities("Scandal") > 0, _.chooseBranch("An elaborate strategy")),
    "The Fallen Angel" -> Conditional(_.qualities("Wounds") < 7, implicit c => {gear.dangerous(); c.chooseBranch("Tackle the verger")}),  //conn: church and a second chance
    "Wanted: Reminders of Brighter Days" -> Conditional(_.items("Incendiary Gossip") >= 25, _.chooseBranch("The tiniest of classified advertisements")),
    "Mr Wines is holding a sale!" -> Conditional(_.items("Romantic Notion") >= 80, _.chooseBranch("A discount for purchase in bulk")),
    "The Ambassador's ball" -> Conditional(c => c.persuasive > 80 && c.persuasive < 119, _.chooseBranch("Making a point of not making a point")),
    //eventually, use "Call on the services of a great mind of the Surface" for 4 scraps - requires high scholar
    "The Correspondence Savages Your Dreams" -> Conditional(_.qualities("Nightmares") < 7, implicit c => { gear.watchful(); c.chooseBranch("Perhaps you can remember something useful") }),
    "Consulting detective required for government work" -> Conditional(_.qualities("Nightmares") < 7, implicit c => { gear.watchful(); c.chooseBranch("Accept the case, but...") }),  //21 proscribed and +subtle
    "A night on the tiles" -> Conditional(_.items("Greyfields 1868 First Sporing") > 0, _.chooseBranch("A bottle of the '68")), //1E of influence
    "Swap Incendiary Gossip" -> Conditional(c => c.items("Incendiary Gossip") > 0 && c.qualities("Connected: Society") >= 50, _.chooseBranch()),
    "The Soft-Hearted Widow" -> Conditional(_.items("Glim") >= 500, _.chooseBranch("Give a significant donation to her charity for the homeless")), //upgrades to 2x stolen kiss, +making waves
    "Help the Sardonic Music-Hall Singer" -> Unplayable, //investigate the other-acquaintance options, otherwise Playable with persuasive-alone option
    "A presumptuous little opportunity" -> Unplayable
  ) withDefaultValue Unplayable, Set(
    //Always useless
    "Putting the pieces together: something about the Fourth City",  //low-chance luck challenge
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
    "Work the carnival",                                    //12 4th relics
    "The young buck",                                       //67 silk or 84 rostygold, never 100%
    "The marvellous contrivance",                           //69 candles or 84 jade, never 100%
    "Moonish water",                                        //1.3E secrets, but -0.5E rostygold, never 100%
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
    "Investigating the Affluent Photographer",              //i have better gear than this gives now
    "Robbing the Ambassador's ball",                        //34 clues or 84 amber
    "Publish your scientific work",                         //48 clues or 81 pearls, never 100%
    "The colour of currency",                               //27 surface currency, never 100%
    "A consideration for services rendered",                //30 brass & 2 appallings for 1 soul, -conn:hell
    "A day at the races",                                   //fatelocked sidequests area, i've already done it 
    "Revolution and Coffee",                                //affluent photographer - completed this storyline both ways
    "The assassin",                                         //84 rostygold
    "The simple joys of villainy",                          //21 or 36 beeswax
    "The Eye and the Camera",                               //80 beeswax
    "A night at the carnival",                              //60 rats
    "Spy on the Black Ribbon",                              //caps too low- a recipe for suspicion
    "Map the new heavens",                                  //small watchful range, <1E rewards
    "Tea with the Inspector",                               //i have outlevelled this
    "Memoirs of a butler",                                  //narrow shadowy range, 84 glim
    "A private detective",                                  //<1E clues
    "Bounty Hunting",                                       //81 rostygold or 96 jade
    "The Dean's distress",                                  //60 candles      
    "Lizardly matters",                                     //50% chance of 4 memories of light, for 25 beeswax - <1E EV
    "Expedition to Winewound Heath",                        //narrow range, <1E
    "Ask Madame Shoshana to cast your horoscope",           //recasting sign requires fate
    "The Northbound Parliamentarian",                       //3 appalling secrets and WTFC
    "A new piece in the Game",                              //45 clues, narrow shadowy range
    "Weather at last",                                      //quirks up to a point
    "Where did that come from?",                            //50 rostygold or start the tournament of lilies
    "A squire of the Flit",                                 //max shadowy 106, not 100%
    "Coats and souls",                                      //60 secrets
    "The Ways of the University",
    "The Ways of the Flit",
    "The Ways of the Forgotten Quarter",
    "The Ways of the Labyrinth of Tigers",
    "The Ways of Wolfstack Docks",
    "The Ways of the Shuttered Palace",
    "The Ways of Mahogany Hall",
    
    
    
    //Useless unless they are useful
    "Pass the Cat: a wriggling delivery",         //the only benefit is -scandal, which i might not need
    "Wanted: Reminders of Brighter Days",         //it's not worth keeping around on the off-chance of saving an action
    "The Ambassador's ball",                      //might not be in the range to play it - only gives a confident smile
    "A consideration for services rendered",      //not worth grinding souls for
    "An unusual wager",                           //only useful when Counting the Days
    "Graffiti with a sting",                      //only useful when Counting the Days
    "A night on the tiles",                       //doesn't pay well without the wine
    "Altars and alms-houses: the Church",         //i don't always want to spend Connected
    "Invited to another revel of the Masters",    //only raises connected from 0 to 1
    "Mirrors and Clay",                           //Vision and Touched by Fingerwork, but 1 vision is 0.5E and touched doesn't seem useful anymore..
    "The Alleys of London: the Criminals",
    "Whispers from the Surface: The Great Game",
    "Swap Incendiary Gossip"
  )
)