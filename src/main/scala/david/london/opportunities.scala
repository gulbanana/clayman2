package david.london
import api._
import common._
import david.gear

object opportunities extends Opportunist(
  Map(
    /*****************/
    /* LODGING CARDS */
    /*****************/
    "The Tower of Sparrows" -> Play(_.chooseBranch("Settle down to a game of cards")),                                                            //persuasive t2
    "The Sleepless Tower" -> Play(_.chooseBranch("Spores and fangs")),                                                                            //dangerous t2 
    "The Tower of Knives" -> Play(_.chooseBranch("Rough camaraderie")),                                                                           //shadowy t2
    "The Tower of Sleeping Giants" -> Play(c => c.chooseBranch(if (c.items("An Infernal Contract") < 100) "The owner" else "Examine the stock")), //watchful t2
    "The Tower of Eyes" -> HoldUntil(_.qualities("Connected: The Masters of the Bazaar") == 0, _.chooseBranch("Do a little promenading yourself")),                 //persuasive t2.5
    "The Heron Tower" -> Play(_.chooseBranch("Hunt down a huge lizard")),                                                                         //dangerous t2.5
    "The Listing Tower" -> Hold, //I think I'm too Dangerous to ever get this?                                                                  //dangerous t2.5
    "The Windward Tower" -> Play(_.chooseBranch("The cautious contact")),                                                                         //shadowy t2.5
    "The High Castle" -> Play(_.chooseBranch("A stroll with a sack")),                                                                            //shadowy t2.5
    "The Lofty Tower" -> Play(_.chooseBranch("Engage in commerce")),                                                                              //persuasive t3
    "The Western Tower" -> Hold,                                                                                                                //watchful t3
    "The Tower of Sun and Moon" -> Hold,     
    
    //watchful t3
    
    /********************/
    /* CONNECTION CARDS */
    /********************/
    "Altars and alms-houses: the Church" -> DiscardUnless(c => c.qualities("Connected: The Church") >= 30 || c.items("Rostygold") >= 10, c =>
      if (c.qualities("Connected: The Church") >= 30)
        c.chooseBranch("Attend a private lecture given by the Bishop of Southwark")
      else 
        c.chooseBranch("Attend a church fête on the south bank of the River")
    ),
    "Court and Cell: the Constables" -> DiscardUnless(c => c.qualities("Connected: Constables") >= 30 || c.items("Rostygold") >= 10, implicit c =>
      if (c.qualities("Connected: Constables") >= 30) {
        gear.watchful()
        c.chooseBranch("Attend a class given by the Implacable Detective")
      } else {
        c.chooseBranch("A small donation")
      }
    ),
    "By the River's Side: the Docks" -> DiscardUnless(c => c.qualities("Connected: The Docks") >= 30 || c.items("Rostygold") >= 10, implicit c =>
      if (c.qualities("Connected: The Docks") >= 30) {
        gear.dangerous()
        c.chooseBranch("Fencing lessons with a Dashing Captain")
      } else {
        c.chooseBranch("Buy a round at the Rusty Tramp")
      }
    ),
    "Burning Shadows: the Devils of London" -> Play(implicit c => 
      if (c.qualities("Connected: Hell") >= 30) {
        gear.watchful()
        c.chooseBranch("Speak with a senior deviless")
      } else {
        c.chooseBranch("Attend a lecture at the Brass Embassy")
      }
    ),
    "The Demi-Monde: Bohemians" -> DiscardUnless(c => c.qualities("Connected: Bohemian") >= 60 || c.items("Greyfields 1882") >= 2, c => 
      if (c.qualities("Connected: Bohemian") >= 60)
        c.chooseBranch("Take tea with a Reclusive Novelist")
      else
        c.chooseBranch("Buy drinks for writers")
    ),
    "Bandages and Dust: The Tomb-Colonies" -> DiscardUnless(_.qualities("Connected: The Tomb-Colonies") >= 30, implicit c => {
      c.chooseBranch("Spar with a Black Ribbon Duellist") 
    }),
    "Whispers from the Surface: The Great Game" -> DiscardUnless(_.qualities("Connected: The Great Game") >= 30, implicit c => {
      gear.watchful()
      c.chooseBranch("Learn more at the carnival")
    }),
    "The Roof-Tops: Urchins" -> DiscardUnless(c => (c.items("Glim") >= 20 && c.qualities("Connected: Urchins") >= 30) || c.items("Lucky Weasel") >= 1, implicit c => {
      gear.shadowy()
      if (c.items("Glim") >= 20 && c.qualities("Connected: Urchins") >= 30)
        c.chooseBranch("Out you go, longshanks")
      else
        c.chooseBranch("In the shadow of All Christs Spire")
    }),
    "Park and Palace: Society" -> Play(c => 
      if (c.qualities("Connected: Society") >= 60)
        c.chooseBranch("Take port with the Veteran Privy Counsellor") //+10cp persuasive, -400cp connected
      else
        c.chooseBranch("An invitation to dinner")   //+connected, -wounds
    ),
    "The Alleys of London: the Criminals" -> DiscardUnless(_.qualities("Connected: Criminals") >= 30, implicit c => {
      gear.shadowy()
      c.chooseBranch("Consult with a master thief")
    }), 
    "Gunpowder and Zeal: the Revolutionaries" -> Play(_.chooseBranch("Taking a walk down gin lane")), //counting the days
    
    
    
    /******************/
    /* CONFLICT CARDS */
    /******************/
    "The Devil and the Child" -> Play(c =>
      if (c.qualities("Connected: Hell") <= c.qualities("Connected: Urchins"))
        c.chooseBranch("Take the Devil's side")
      else
        c.chooseBranch("Convince the urchin to keep his soul")
    ),
    "Going gentle" -> Play(implicit c =>
      if (c.qualities("Connected: The Tomb-Colonies") <= c.qualities("Connected: Society")) {
        gear.dangerous()
        c.chooseBranch("Break him out!") 
      } else {
        c.chooseBranch("Discreetly inform the family about the baronet's communication")
      }
    ),
    "A contact in the Great Game has a tale for you" -> Play(c =>
      if (c.qualities("Connected: The Great Game") <= c.qualities("Connected: The Church")) {
        c.chooseBranch("Make it clear that no-one leaves the Game") 
      } else {
        c.chooseBranch("Everyone deserves a second chance")
      }
    ),
    "Youthful high spirits" -> Play(c =>
      if (c.qualities("Connected: The Docks") <= c.qualities("Connected: Urchins")) {
        c.chooseBranch("Sabotage the cannon") 
      } else {
        c.chooseBranch("Convince the residents to pay their protection fees to the urchins instead")
      }
    ),
    "Brimstone or frankincense?" -> Play(c =>
      if (c.qualities("Connected: Hell") <= c.qualities("Connected: The Church")) {
        c.chooseBranch("Firecrackers in the thurible!") 
      } else {
        c.chooseBranch("Alert the vicar")
      }
    ),
    "The kaleidoscopic church" -> Play(c =>
      if (c.qualities("Connected: Bohemian") <= c.qualities("Connected: The Church")) {
        c.chooseBranch("Secure the artists’ work") 
      } else {
        c.chooseBranch("Make it clear the Bohemians aren’t welcome any more")
      }
    ),
    "A familiar face by the school railings" -> Play(c =>
      if (c.qualities("Connected: The Orient") <= c.qualities("Connected: Urchins")) {
        c.chooseBranch("Advise the girl to return to the Widow") 
      } else {
        c.chooseBranch("Arrange for the girl to return to the urchin-gangs")
      }
    ),
    "Amber in the well" -> Play(c =>
      if (c.qualities("Connected: Revolutionaries") <= c.qualities("Connected: Rubbery Men")) {
        c.chooseBranch("Convince the Rubbery Men to move on") 
      } else {
        c.chooseBranch("Convince the Revolutionaries to find somewhere else")
      }
    ),
    "They all look the same to me" -> Play(c =>
      if (c.qualities("Connected: Rubbery Men") <= c.qualities("Connected: Constables")) {
        c.chooseBranch("Finger a scapegoat.") 
      } else {
        c.chooseBranch("Finger the guilty party")
      }
    ),
    "A misfortune at the Carnival" -> Play(c =>
      if (c.qualities("Connected: Rubbery Men") <= c.qualities("Connected: The Tomb-Colonies")) {
        c.chooseBranch("Save the Rubbery Man") 
      } else {
        c.chooseBranch("Save the Tomb-Colonist!")
      }
    ),
    "The Tomb-colonist and the Footpad" -> HoldUntil(_.items("Intriguing Gossip") >= 1, c =>
      if (c.qualities("Connected: Criminals") <= c.qualities("Connected: The Tomb-Colonies"))
        c.chooseBranch("Accept the job")
      else
        c.chooseBranch("Double-cross him")
    ),
    "A tavern dust-up" -> HoldUntil(_.items("Cryptic Clue") >= 10, c => {
      val faction = Set("Connected: The Constables", "Connected: The Church", "Connected: The Docks").min(Ordering.by(c.qualities))
      c.chooseBranch("Intervene to help the " + Map(
        "Connected: The Constables" -> "Constable",
        "Connected: The Church" -> "cleric",
        "Connected: The Docks" -> "docker"
      )(faction))
    }),
    "Valuable Secrets" -> HoldUntil(_.items("Whispered Secret") >= 15, c => {
      val faction = Set("Connected: Hell", "Connected: The Great Game", "Connected: Bohemian").min(Ordering.by(c.qualities))
      c.chooseBranch(Map(
        "Connected: Hell" -> "Sell the information to the Embassy",
        "Connected: The Great Game" -> "Find a buyer for the information",
        "Connected: Bohemian" -> "Warn the artist"
      )(faction))
    }),
    "A scuffle on the street" -> Play(c => {
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
    "The Awful Temptation of Money" -> Play,
    "Graffiti with a sting" -> DiscardUnless(_.qualities("Counting the Days") >= 10, _.chooseBranch("Ask someone else what they saw")),
    "An unusual wager" -> DiscardUnless(_.qualities("Counting the Days") < 10, _.chooseBranch("Look at those coins")),
    "The Law's Long Arm" -> Play(c => {
      c.chooseBranch(deprioritise(c.branches, "Official incompetence"))
    }),
    "A Moment's Peace" -> Play(c => {
      c.chooseBranch(deprioritise(c.branches - "Follow a light into the trees", "Relax and enjoy"))
    }),
    "A Restorative" -> Play(c => {
      c.chooseBranch(deprioritise(c.branches - "A sumptuous repast!", "Scraps from the table"))
    }),
    "An afternoon of good deeds?" -> Play(c => {
      c.chooseBranch(deprioritise(c.branches - "A plaster saint!" - "An afternoon of mischief!", "Quite a moral afternoon."))
    }),
    
    
    
    /****************/
    /* WILMOT'S END */
    /****************/
    "Less fierce than he looks" -> Play,
    
    
    /****************/
    /* DOUBT STREET */
    /****************/
    "The Illuminated Gentleman Takes the Stage" -> Play(implicit c => {gear.shadowy(); c.chooseBranch()}), //6x Salacious Copy
    
    
    /**********************/
    /* ACQUAINTANCE CARDS */
    /**********************/
    "An Old Acquaintance?" -> Play(implicit c => {
      gear.persuasive()
      c.chooseBranch("Call on her")  //1.1E of stuff and connections 
    }), 
    "Help the Sardonic Music-Hall Singer" -> Play(implicit c => {
      gear.persuasive()
      c.chooseBranch("Help her negotiate")  //?
    }),
    "Ask the Sardonic Music-Hall Singer to help you" -> Play(implicit c => {
      gear.persuasive()
      c.chooseBranch("An invitation to a rather exclusive soirée")  //1.2E of gossip, or rare Aeolian Scream
    }),
    "A Visit" -> Play(c => {
      val friend = Set("Acquaintance: a Repentant Forger", "Acquaintance: Regretful Soldier", "Acquaintance: Sardonic Music-Hall Singer", "Acquaintance: Wry Functionary").min(Ordering.by(c.qualities))
      c.chooseBranch(Map(
        "Acquaintance: a Repentant Forger" -> "The Repentant Forger",
        "Acquaintance: Regretful Soldier" -> "Visit the Regretful Soldier",
        "Acquaintance: Sardonic Music-Hall Singer" -> "Visit the Sardonic Music-Hall Singer",
        "Acquaintance: Wry Functionary" -> "Visit the Wry Functionary"
      )(friend))
    }),
    
    
    
    /***************/
    /* OTHER CARDS */
    /***************/
    "A rather decadent evening" -> Play,
    "A day without laudanum" -> Autofire,
    "A parliament of bats" -> Play(_.chooseBranch("Release a bat into the cloud")),
    "Investigate the Topsy King's court" -> Play(_.chooseBranch("Spy on the dealings with revolutionaries")), //conn:const and 64 rostygold
    "The Geology of Winewound" -> Play(implicit c => { gear.watchful(); c.chooseBranch("Go as far as you can") }), //130E of relics, rare for visions
    "The Parthenaeum" -> Play(implicit c => { gear.dangerous(); c.chooseBranch("Roust out an interloper") }),  //conn:soc and 0.6E wines 
    "One's public" -> Play(implicit c => { gear.persuasive(); c.chooseBranch("Put on a fine show for them") }), //>2E of stuff!
    "A riot in Spite!" -> Play(_.chooseBranch("Wade in with fists flying")), //connections
    "The Cities that Fell" -> Play(implicit c => { gear.watchful(); c.chooseBranch("Ancient stories") }), //with POSI, 3 visions
    "Devices and desires" -> Play(implicit c => { gear.watchful(); c.chooseBranch("The trade in clocks") }), //with POSI, 10 notions
    "His Young Lordship seized by tentacles" -> Play(_.chooseBranch("Sell snacks to the crowd")),
    "Minding the detective" -> Play(implicit c => { gear.dangerous(); c.chooseBranch("The case of the frenzied mandrake") }),                   //business card (and 60 rostygold)
    "All fear the Overgoat!" -> Play(implicit c => {gear.watchful(); c.chooseBranch("Learn of the Overgoat")}),
    "The Seekers of the Garden" -> Play(implicit c => {gear.watchful(); c.chooseBranch("Leisurely enquiries")}),                                //3 MODS - 1.5E
    "A library of your own" -> Play(_.chooseBranch("Diligent research")),                                                                       //50% 1.5E clues, 50% 1.05E stuff
    "Rat Melancholy" -> Play(_.chooseBranch("Let her grieve in dignified silence")),                                                            //50 '82, 3cp rat sympathy
    "A past benefactor" -> Play(_.chooseBranch("And what of the secrets of Hell?")),                                                            //sudden insight
    "The Paronomastic Newshound" -> Play(_.chooseBranch("Talk to him about the Tomb-Colonies")),  
    "The little people" -> Play(_.chooseBranch("Do your best for him")),                                                                        //80 pearls, 5 conn:crim, rare=bribe
    "Riding your Velocipede" -> Play(_.chooseBranch("The velocipede courier")),
    "The tomb-colonist's dogs" -> Play(_.chooseBranch("Could you look after them for a day?")),                                                 //61 candle, +10cp colonies
    "Below the Neath" -> Play(_.chooseBranch("Go and see what else you can find")),                                                             //70 souls
    "Investigate Doctor Schlomo" -> Play(implicit c => {
      gear.watchful()
      if (c.qualities("Nightmares") > 2)
        c.chooseBranch("Pay the Doctor a visit")                                                                                                    //-3cp, rare: scream
      else
        c.chooseBranch("Interrogate a patient")                                                                                                     //60 clues
    }),                                
    "Cheesemonger no more" -> Play(c => {
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
    }),                                                                                                                                             //+1 HEL and quirks up to 9
    "A Day with God's Editors" -> Play(c => {
      if (c.qualities("Scandal") > 0 && c.qualities("Nightmares") > 0) 
        c.chooseBranch("Work diligently")                                                                                                           //-1cp of each and 5cp conn:church
      else 
        c.chooseBranch("Examine the latest revisions")
    }),                                                                                                                                             //+1 HEL and quirks up to 9
    "A relaxed day at the Club" -> Play(c =>
      if (c.qualities("Suspicion") > 1) {
        c.chooseBranch("Have a little word with the Chief Constable")
      } else {
        c.chooseBranch("Fall asleep in front of the fire")
      }
    ),
    "A Rubbery Man lopes purposefully in your wake, tentacles dangling like hanged men's fingers" -> Play(_.chooseBranch("Give it the Amber")), //+100 deep amber and +5 cp rubbery
    "What's in the sack, Jack?" -> Discard,                      //18 proscribed material
    "Rob a library at the University" -> HoldUntil(_.qualities("Suspicion") < 7, implicit c => { gear.shadowy(); c.chooseBranch() }),             //conn: rev and 15 proscribed
    "Pass the Cat: a wriggling delivery" -> DiscardUnless(_.qualities("Scandal") > 0, _.chooseBranch("An elaborate strategy")),
    "The Fallen Angel" -> HoldUntil(_.qualities("Wounds") < 7, implicit c => {gear.dangerous(); c.chooseBranch("Tackle the verger")}),            //conn: church and a second chance
    "Wanted: Reminders of Brighter Days" -> DiscardUnless(_.items("Incendiary Gossip") >= 25, _.chooseBranch("The tiniest of classified advertisements")),
    "Mr Wines is holding a sale!" -> HoldUntil(_.items("Romantic Notion") >= 80, _.chooseBranch("A discount for purchase in bulk")),
    "The Ambassador's ball" -> DiscardUnless(c => c.persuasive > 80 && c.persuasive < 119, _.chooseBranch("Making a point of not making a point")),
    //eventually, use "Call on the services of a great mind of the Surface" for 4 scraps - requires high scholar
    "The Correspondence Savages Your Dreams" -> HoldUntil(_.qualities("Nightmares") < 7, implicit c => { gear.watchful(); c.chooseBranch("Perhaps you can remember something useful") }),
    "Consulting detective required for government work" -> HoldUntil(_.qualities("Nightmares") < 7, implicit c => { gear.watchful(); c.chooseBranch("Accept the case, but...") }),  //21 proscribed and +subtle
    "A night on the tiles" -> DiscardUnless(_.items("Greyfields 1868 First Sporing") > 0, _.chooseBranch("A bottle of the '68")),                     //1E of influence
    "Swap Incendiary Gossip" -> DiscardUnless(c => c.items("Incendiary Gossip") > 0 && c.qualities("Connected: Society") >= 50, _.chooseBranch()),
    "The Soft-Hearted Widow" -> DiscardUnless(_.items("Glim") >= 500, _.chooseBranch("Give a significant donation to her charity for the homeless")), //upgrades to 2x stolen kiss, +making waves
    "A presumptuous little opportunity" -> HoldUntil(
      c => c.items("Greyfields 1882") >= 1000 || c.items("Morelways 1872") >= 400 || c.items("Greyfields 1879") >= 5000 || c.items("Cellar of Wine") >= 5,
      c => if (c.items("Greyfields 1879") >= 5000) {
        c.chooseBranch("Perhaps you're interested in an exceptional vintage?")
      } else if (c.items("Morelways 1872") >= 400) {
        c.chooseBranch("An alarming amount of Strangling Willow Absinthe")
      } else if (c.items("Greyfields 1882") >= 1000) {
        c.chooseBranch("Ten cases of Morelways")
      } else {  
        c.perhapsNot()                                                                                                                              //can trade cellars for airag, but there's no reason to do so and it costs 3 actions
        c.discardOpportunity("A presumptuous little opportunity")
      }
    ),
    "1000 Nevercold Brass wanted! Will pay handsomely!" -> HoldUntil(_.items("Nevercold Brass Sliver") >= 1000, _.chooseBranch("Make the exchange")) //+15cp Great Game
  ) withDefaultValue Hold, Set(
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
    "Bringing the revolution",                              //quirks up to 6 and 1cp shadowy
    "A street-cart is selling Fourth City Rags",            //i do not rags
    "They Want to Hear of the Vake",                        //no friends on the bag a legend ambition
    "Baying for Blood",                                     //no friends on the nemesis
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
    //"Invited to another revel of the Masters",    //only raises connected from 0 to 1
    "Mirrors and Clay",                           //Vision and Touched by Fingerwork, but 1 vision is 0.5E and touched doesn't seem useful anymore..
    "The Alleys of London: the Criminals",
    "An implausible penance",                     //trades in conn: criminals for rewards, e.g. 500cp for ~30 echoes
    "Whispers from the Surface: The Great Game",
    "Swap Incendiary Gossip"
  )
)