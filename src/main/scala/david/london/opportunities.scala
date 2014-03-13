package david
import api._
import common._

package object london {
  private val watchful = Map(
    "Academic discipline" -> Discard,                                                                                                                 //0.6E '79,
    "A day in the garden" -> Discard,                                                                                                                 //0.6E secrets
    "Undertakings" -> Discard,                                                                                                                        //too-narrow a watchful range
    "Recapturing a prison escapee" -> Discard,                                                                                                        //too-narrow a watchful range
    "The Eye and the Camera" -> Discard,                                                                                                              //80 beeswax
    "Lies below the Palace" -> Discard,                                                                                                               //18 proscribed (<0.8E)
    "Tea with the Inspector" -> Discard,                                                                                                              //i have outlevelled this
    "Map the new heavens" -> Discard,                                                                                                                 //small watchful range, <1E rewards
    "Property most intellectual" -> Discard,                                                                                                          //<1E correspondence
    "Publish your scientific work" -> Discard,                                                                                                        //48 clues or 81 pearls, never 100%
    "Expedition to Winewound Heath" -> Discard,                                                                                                       //narrow range, <1E
    "Medical Emergency" -> Discard,                                                                                                                   //<50 secrets
    "Infiltrate a gentlemen's club" -> Discard,                                                                                                       //0.6E stuff, connections but can't be menace-free
    "The colour of currency" -> Discard,                                                                                                              //27 surface currency, never 100%
    "The Dean's distress" -> Discard,                                                                                                                 //60 candles
    "A private detective" -> Discard,                                                                                                                 //<1E clues
    "Coals and souls" -> Discard,                                                                                                                     //60 secrets
    "The mechanics of progress" -> Play,                                                                                                              //115+ secrets (Watchful dependent?)
    "Investigate the Topsy King's court" -> Play(_.chooseBranch("Spy on the dealings with revolutionaries")),                                         //conn:const and 64 rostygold
    "The Correspondence Savages Your Dreams" -> Play(implicit c => { gear.watchful(); c.chooseBranch("Perhaps you can remember something useful") }), //eventually, use "Call on the services of a great mind of the Surface" for 4 scraps - requires high scholar
    "Consulting detective required for government work" -> Play(implicit c => { gear.watchful(); c.chooseBranch("Accept the case, but...") }),        //21 proscribed and +subtle
    "The Geology of Winewound" -> Play(implicit c => { gear.watchful(); c.chooseBranch("Go as far as you can") }),                                    //130E of relics, rare: visions
    "Investigate Doctor Schlomo" -> Play(implicit c => {
      gear.watchful()
      if (c.nightmares > 2)
        c.chooseBranch("Pay the Doctor a visit")                                                                                                      //-3cp, rare: scream
      else
        c.chooseBranch("Interrogate a patient")                                                                                                       //60 clues
    })
  )
  
  private val shadowy = Map(
    "Help a spy distract an inconvenient tail" -> Discard,                                                          //36 jade
    "Stark villainy" -> Discard,                                                                                    //36 rostygold
    "The ever-present and invisible servantry" -> Discard,                                                          //<0.4E stuff
    "A meeting of cats" -> Discard,                                                                                 //24 clues, never 100%
    "Rob a Brass Embassy courier" -> Discard,                                                                       //15 proscribed material (0.6E)
    "Robbing the Ambassador's ball" -> Discard,                                                                     //34 clues or 84 amber
    "Memoirs of a butler" -> Discard,                                                                               //narrow shadowy range, 84 glim
    "A squire of the Flit" -> Discard,                                                                              //max shadowy 106, not 100%
    "Spy on the Black Ribbon" -> Discard,                                                                           //caps too low- a recipe for suspicion
    "Work the carnival" -> Discard,                                                                                 //12 4th relics
    "The marvellous contrivance" -> Discard,                                                                        //69 candles or 84 jade, never 100%
    "Race across the river" -> Discard,                                                                             //84 glim
    "A simple job from a devil" -> Discard,                                                                         //72 brass
    "As silent as the grave?" -> Discard,                                                                           //0.75E misc goods
    "The simple joys of villainy" -> Discard,                                                                       //21 or 36 beeswax  
    "A new piece in the Game" -> Discard,                                                                           //45 clues, narrow shadowy range
    "Reeducating Lyme" -> Discard,                                                                                  //requires shadowy 180+ for a good chance
    "Revisit the Theosophisticals" -> Discard,                                                                      //"Make your own" is 1.3E but too low a success chance for now
    "A fine day in the Flit" -> Play(implicit c => {gear.shadowy(); c.chooseBranch("Go hunting for caches")}),                             //30 proscribed material, 1.2E
    "The Phantom of the Antimacassar" -> Play(_.chooseBranch("Become the Phantom!")),                               //5 diamond, 60 moonpearl
    "The little people" -> Play(_.chooseBranch("Do your best for him")),                                            //80 pearls, 5 conn:crim, rare=bribe
    "Below the Neath" -> Play(implicit c => {gear.shadowy(); c.chooseBranch("Go and see what else you can find")}), //70 souls
    "Rob a library at the University" -> Play(implicit c => {gear.shadowy(); c.chooseBranch()})                     //conn: rev and 15 proscribed
  )
      
  private val dangerous = Map(
    "The tenor's minder" -> Discard,                                                                                              //reward low, dangerous range too small
    "A runaway horse!" -> Discard,                                                                                                //reward low, dangerous range too small
    "The young buck" -> Discard,                                                                                                  //67 silk or 84 rostygold, never 100%
    "Ambushed by pirates!" -> Discard,                                                                                            //72 glim, never 100%
    "Moonish water" -> Discard,                                                                                                   //1.3E secrets, but -0.5E rostygold, never 100%
    "An evening's zailing" -> Discard,                                                                                            //bad conn card - always +1 to one, -5 to the other
    "What's in the sack, Jack?" -> Discard,                                                                                       //18 proscribed material
    "The assassin" -> Discard,                                                                                                    //84 rostygold
    "A night at the carnival" -> Discard,                                                                                         //60 rats
    "Bounty Hunting" -> Discard,                                                                                                  //81 rostygold or 96 jade
    "Shroom-hopping: a quaint sport of the lower classes" -> Discard,                                                             //18 '82
    "Minding the detective" -> Play(implicit c => { gear.dangerous(); c.chooseBranch("The case of the frenzied mandrake") }),     //business card (and 60 rostygold)
    "A riot in Spite!" -> Play(_.chooseBranch("Wade in with fists flying")),                                                      //connections
    "The Parthenaeum" -> Play(implicit c => { gear.dangerous(); c.chooseBranch("Roust out an interloper") }),                     //conn: soc and 0.6E wines 
    "The Fallen Angel" -> Play(implicit c => {gear.dangerous(); c.chooseBranch("Tackle the verger")}),                            //conn: church and a second chance
    "His Young Lordship seized by tentacles" -> Play(_.chooseBranch("Sell snacks to the crowd")),                                 //conn: rev
    "The tomb-colonist's dogs" -> Play(implicit c => {gear.dangerous(); c.chooseBranch("Could you look after them for a day?")})  //61 candle, +10cp colonies
  )
          
  private val persuasive = Map(
    "A deviless' serenade" -> Discard, //60 amber
    "Romance and practicality" -> Discard,                                                                      //36 whispered secrets
    "The marriage of inconvenience" -> Discard,                                                                 //50 whispered secrets
    "The notable citizen" -> Discard,                                                                           //83 whispered secrets
    "One's public" -> Play(implicit c => { gear.persuasive(); c.chooseBranch("Put on a fine show for them") }), //>2E of stuff!
    "The Paronomastic Newshound" -> Play(_.chooseBranch("Talk to him about the Tomb-Colonies")),                //extraordinary implication
    "The Ambassador's ball" -> DiscardUnless(c => c.persuasive > 80 && c.persuasive < 119, _.chooseBranch("Making a point of not making a point"))
  )
  
  private val routes = Map(
    "The Ways of the University" -> Discard,
    "The Ways of the Flit" -> Discard,
    "The Ways of the Forgotten Quarter" -> Discard,
    "The Ways of the Labyrinth of Tigers" -> Discard,
    "The Ways of Wolfstack Docks" -> Discard,
    "The Ways of the Shuttered Palace" -> Discard,
    "The Ways of Mahogany Hall" -> Discard
  )
  
  private val lodgings = Map(
    "The Tower of Sparrows: Vice and Virtue in a Gambling Den" -> Play(_.chooseBranch("Settle down to a game of cards")), //persuasive t2 - 1 scrap
    "The Sleepless Tower: Disturbances at a Cottage by the Observatory" -> Play(_.chooseBranch("Spores and fangs")),   //dangerous t2 - 1 scrap
    "The Tower of Knives: Difficulties at a Smoky Flophouse" -> Play(_.chooseBranch("Rough camaraderie")),             //shadowy t2 - 1 scrap
    "The Tower of Sleeping Giants: The Secrets of the Rooms above a Bookshop" -> Play("Examine the stock"),               //watchful t2 - 1 scrap
    
    "The Heron Tower: Events at a Lair in the Marshes" -> Play(_.chooseBranch("Hunt down a huge lizard")),       //dangerous t2.5
    "The Windward Tower: the Matter of the Decommissioned Steamer" -> Play(_.chooseBranch("The cautious contact")),    //shadowy t2.5
    "The High Castle: What Occurs in a Rooftop Shack" -> Play(_.chooseBranch("A stroll with a sack")),                //shadowy t2.5
    "The Tower of Eyes: Behind Closed Doors at a Handsome Townhouse" -> Hold/*Until(
      c => c.qualities("Connected: Bohemian") < 50 && c.qualities("Connected: Society") < 50, 
      c => c.chooseBranch("Do a little promenading yourself"))*/,    //salon
    "The Listing Tower" -> Hold,                                     //requires knife and candle stuff
    
    "The Lofty Tower: the Potential of Premises at the Bazaar" -> Play(implicit c => {
      gear.persuasive()
      c.chooseBranch("Engage in commerce")                           //4 scraps, money
    }),
    
    "The Western Tower: a Guest Room at the Brass Embassy" -> Play("Find out more about the soulless"),       //3 scraps
    
    "The Tower of Sun and Moon: a Reservation at the Royal Bethlehem Hotel" -> Play("Investigate the guests") //3 scraps
  )
  
  private val connections = Map(
    "Gunpowder and Zeal: the Revolutionaries" -> DiscardUnless(c => c.qualities("Counting the Days") < 14 || c.dangerous < 175, c => {
      if (c.qualities("Counting the Days") < 14)
        c.chooseBranch("Taking a walk down gin lane")
      else
        c.chooseBranch("And now... bombs!")
    }),        
    "Altars and alms-houses: the Church" -> DiscardUnless(c => c.qualities("Connected: The Church") >= 50 || c.items("Rostygold") >= 10, c =>
      if (c.qualities("Connected: The Church") >= 30)
        c.chooseBranch("Attend a private lecture given by the Bishop of Southwark")
      else 
        c.chooseBranch("Attend a church fête on the south bank of the River")
    ),
    "Court and Cell: the Constables" -> DiscardUnless(c => c.qualities("Connected: Constables") >= 50 || c.items("Rostygold") >= 10, implicit c =>
      if (c.qualities("Connected: Constables") >= 50) {
        c.chooseBranch("Attend a class given by the Implacable Detective")
      } else {
        c.chooseBranch("A small donation")
      }
    ),
    "By the River's Side: the Docks" -> DiscardUnless(c => c.qualities("Connected: The Docks") >= 100 || c.items("Rostygold") >= 10, implicit c =>
      if (c.qualities("Connected: The Docks") >= 100) {
        c.chooseBranch("Fencing lessons with a Dashing Captain")
      } else {
        c.chooseBranch("Buy a round at the Rusty Tramp")
      }
    ),
    "Burning Shadows: the Devils of London" -> Play(implicit c => 
      if (c.qualities("Connected: Hell") >= 100) {
        c.chooseBranch("Speak with a senior deviless")
      } else {
        c.chooseBranch("Attend a lecture at the Brass Embassy")
      }
    ),
    "The Demi-Monde: Bohemians" -> DiscardUnless(c => c.qualities("Connected: Bohemian") >= 60 || c.items("Greyfields 1882") >= 2, c => 
      if (c.qualities("Connected: Bohemian") >= 100)
        c.chooseBranch("Take tea with a Reclusive Novelist")
      else
        c.chooseBranch("Buy drinks for writers")
    ),
    "Bandages and Dust: The Tomb-Colonies" -> DiscardUnless(_.qualities("Connected: The Tomb-Colonies") >= 100, implicit c => {
      c.chooseBranch("Spar with a Black Ribbon Duellist") 
    }),
    "Whispers from the Surface: The Great Game" -> DiscardUnless(_.qualities("Connected: The Great Game") >= 100, implicit c => {
      c.chooseBranch("Learn more at the carnival")
    }),
    "The Roof-Tops: Urchins" -> DiscardUnless(c => (c.items("Glim") >= 20 && c.qualities("Connected: Urchins") >= 50) || c.items("Lucky Weasel") >= 1, implicit c => {
      if (c.items("Glim") >= 20 && c.qualities("Connected: Urchins") >= 50)
        c.chooseBranch("Out you go, longshanks")
      else
        c.chooseBranch("In the shadow of All Christs Spire")
    }),
    "Park and Palace: Society" -> Play(c => 
      //if (c.qualities("Connected: Society") >= 60)
      //  c.chooseBranch("Take port with the Veteran Privy Counsellor")  //+10cp persuasive, -400cp connected
      //else
        c.chooseBranch("An invitation to dinner")                      //+connected, -wounds
    ),
    "The Alleys of London: the Criminals" -> DiscardUnless(_.qualities("Connected: Criminals") >= 100, implicit c => {
      gear.shadowy()
      c.chooseBranch("Consult with a master thief")
    }),
    
    "A consideration for services rendered" -> Discard,                //trades in conn: hell, not worth grinding souls for
    "A limping figure in a top hat beckons" -> Discard,                //trades in conn: rubbery, just gives amber
    "An implausible penance" -> Discard,                               //trades in conn: criminals for interesting but complex rewards, e.g. 500cp for ~30 echoes
    "A merry sort of crime" -> Play(_.chooseBranch("Dirigible theft")),//+15cp conn: criminals
    "A Rubbery Man lopes purposefully in your wake, tentacles dangling like hanged men's fingers" -> Discard, //-1 warm for +100 deep amber and +5 cp rubbery
    "1000 Nevercold Brass wanted! Will pay handsomely!" -> DiscardUnless(
        c => c.items("Nevercold Brass Sliver") >= 1000 && c.qualities("Connected: The Great Game") < 15, 
        _.chooseBranch("Make the exchange")                            //+15cp Great Game, but it can get me trapped in a cycle!
    ) 
  )
  
  private val conflicts = Map(
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
    })
    //"The Acacia and the Butterfly" - this is actually +30, -500! rewards are good, but safer is to use it for the Affair of the Box
  )
  
  private val acquaintances = Map(
    "An Old Acquaintance?" -> Play(implicit c => {
      gear.persuasive()
      c.chooseBranch("Call on her")  //1.1E of stuff and connections 
    }), 
    "A Visit" -> Play(c => {
      val friend = Set("Acquaintance: a Repentant Forger", "Acquaintance: Regretful Soldier", "Acquaintance: Sardonic Music-Hall Singer", "Acquaintance: Wry Functionary").min(Ordering.by(c.qualities))
      (Map(
        "Acquaintance: a Repentant Forger" -> (() => {
          c.chooseBranch("The Repentant Forger")
          if (c.branches.contains("Old habits"))
            c.chooseBranch("Old habits")
          else
            c.chooseBranch("Drop by for a chat")
        }),
        "Acquaintance: Regretful Soldier" -> (() => {
          c.chooseBranch("Visit the Regretful Soldier")
          if (c.branches.contains("Take him to the theatre"))
            c.chooseBranch("Take him to the theatre")
          else
            c.chooseBranch("Visit him in his lodgings")
        }),
        "Acquaintance: Sardonic Music-Hall Singer" -> (() => {
          c.chooseBranch("Visit the Sardonic Music-Hall Singer")
        }),
        "Acquaintance: Wry Functionary" -> (() => {
          c.chooseBranch("Visit the Wry Functionary")
          c.chooseBranch("Go for a glass of wine and a chat")
        })
      )(friend))()
    })
  )
  
  private val mysteries = Map(
    //Walking the Falling Cities
    "The Cities that Fell" -> Play(implicit c => {gear.watchful(); c.chooseBranch("Ancient stories")}),          //with POSI, 3 visions
    //Seeing Through the Eyes of Icarus
    "Devices and desires" -> Play(implicit c => {gear.watchful(); c.chooseBranch("The trade in clocks")}),       //with POSI, 10 notions
    "Rat Melancholy" -> Discard,//Play(_.chooseBranch("Let her grieve in dignified silence")),                   //50 '82, 3cp rat sympathy
    //Approaching the Gates of the Garden
    "The Seekers of the Garden" -> Play(implicit c => {gear.watchful(); c.chooseBranch("Leisurely enquiries")}), //3 MODS - 1.5E
    //Touched by Fingerwork
    "Mirrors and Clay" -> Discard
    //"Mirrors and Clay" -> Play(implicit c => {gear.persuasive(); c.chooseBranch("Unfinished Men")}) //1 MODS, and Touched by Fingerwork, but 1 vision is 0.5E and touched doesn't seem useful anymore..
  )
  
  private val items = Map(
    "What will you do with your Partisan Messenger Tortoise?" -> Play(implicit c => {
      gear.respectable()
      c.chooseBranch("Set her to moonlighting")                                          //3x compromising document - 1.5E
    }),
    "Lizardly matters" -> Discard,                                                       //50% chance of 4 memories of light, for 25 beeswax - <1E EV
    "A parliament of bats" -> Play("Release a bat into the cloud"),                      //iirc variable but good rewards
    "Your Corresponding Ocelot is listless" -> Play(c =>
      if (c.scandal > 1) {
        c.chooseBranch("Help him write a letter to Mr Huffam")                           //-scandal
      } else {
        c.chooseBranch("Rub his belly")                                                  //+scholar
      }
    ),
    "Your Dream-Hound" -> Hold,                                                          //"Have the beast guard your resting hours" = -nightmares
    "What Does One Do with a Bifurcated Owl?" -> Play("Feeding time"),                   //+nightmares, tot, 50% of aeolian scream
    "All fear the Overgoat!" -> Discard,                                                 //1x TOT and 4x appalling, also it causes problems when run on server
    "A library of your own" -> Play("Diligent research"),                                //50% 1.5E clues, 50% 1.05E stuff
    "A Day with God's Editors" -> Play(c => {
      if (c.scandal > 0 && c.nightmares > 0) 
        c.chooseBranch("Work diligently")                                                //-1cp of each and 5cp conn:church
      else 
        c.chooseBranch("Examine the latest revisions")                                   //-nightmares & scandal
    }),                                                                                  
    "The Life of Crime" -> Play("Your cut of the take"),                                 //+150 rostygold. or: "Remind them who's boss" -> +crim, +susp
    "A relaxed day at the Club" -> Play(c =>
      if (c.suspicion > 1) {
        c.chooseBranch("Have a little word with the Chief Constable")                    //-suspicion
      } else {
        c.chooseBranch("Fall asleep in front of the fire")                               //secrets
      }
    ),
    "Riding your Velocipede" -> Play("The velocipede courier"),
    "Out and About on your Ratwork Velocipede" -> Play(implicit c => {                   //race colonel: +150 secrets; bathe in admiration: confident smile
      gear.dangerous()
      c.chooseBranch("Race a lieutenant-colonel of lancers")
    }),
    "A Pleasant Day for a Ride" -> DiscardUnless(_.suspicion == 2),                      //50% conn: soc and -suspicion
    "Your Edifice of Black Stone" -> Play("Rent out your gymnasium to ring-fighters"),   //+150 rostygold
    "A day out in your Clay Sedan Chair" -> Play("To make a point of treating them well"), //conn: soc        
    "Once upon a time in a carriage" -> Play("Offer her a lift"),                        //150 whispered, 1 appalling, +conn: soc
    "Overly alluring?" -> Discard
  )
  
  
  //mostly i want to save up scraps for now - 160 starts getting you otherwise-unobtainable items
  private val relickers = Map(
    "The Shivering Relicker and Pinnock are Trundling By" -> Play("Recertify a double-armful of scraps"),             //Wild Words
    "The Capering Relicker and Gulliver are Outside in the Street" -> Play("Recertify a double-armful of scraps"),    //Infernal
    "The Coquettish Relicker and Mathilde are Making the Rounds" -> Play("Recertify a double-armful of scraps"),      //Rag Trade
    "The Curt Relicker and Montgomery are Moving Quietly Past" -> Play("Recertify a double-armful of scraps"),        //Rumour
    "A Gift from the Capering Relicker" -> Play(_.chooseBranch("For someone who has it all, or at least most of it")) //12E - the other options are 10E
  )
  
  private val affluentPhotographer = Map(
    "Investigating the Affluent Photographer" -> Discard, //i have better gear than this gives now
    "Revolution and Coffee" -> Discard                    //and have completed it both ways
  )
  
  private val countingTheDays = Map(
    "The Awful Temptation of Money" -> Discard, //got enough Spending Secrets for now
    "City Vices: Orthographic Infection" -> DiscardUnless(c => c.qualities("Counting the Days") >= 10 && c.qualities("Counting the Days") <= 13, 
                                                          _.chooseBranch("Ask someone else what they saw")),
    "An unusual wager" -> DiscardUnless(c => c.qualities("Counting the Days") < 10, _.chooseBranch("Look at those coins")),
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
    })
  )
  
  private val wilmotsEnd = Map(
    "Less fierce than he looks" -> Play,
    "A Dream about a Missing Woman" -> Play, //1cp or 2cp Missing Woman, 50%
    "A New Move in the Game" -> Discard,     //just gives Doing Business, +1cp - it uses watchful instead of shadowy but who cares
    "The Sights of Wilmot's End" -> Discard  //luck checks for Doing Business
  )
  
  object Publish extends Play(implicit c => {gear.shadowy(); c.chooseBranch()})
  private val doubtStreet = Map(
    "The Illuminated Gentleman Takes the Stage" -> Discard,                 //6 Salacious Copy
    "Another Day, Another Dreary Salon" -> Discard,                         //6 Salacious Copy
    "Fog Like a Velvet Curtain" -> Discard,                                 //6 Salacious Copy - 6 hours
    "A Letter to the Editor" -> Discard,                                    //6 Salacious Copy - 2 hours
    "The Food You Eat" -> Publish,                                          //6 Meritorious Copy - 10 hours
    "A Cancelled Salon" -> Publish,                                         //6 Meritorious Copy - 7 hours
    "Your Day on Court Duty" -> Publish,                                    //6 Meritorious Copy - 4 hours
    "Reports of your Circulation have been Greatly Exagerrated" -> Publish, //6 Meritorious Copy - 2 hours
    "An Interview with a 'Foreign Office Insider'" -> Discard,              //6 Outlandish Copy
    "The Cloaked Menace of Cake Street" -> Discard,                         //6 Outlandish Copy
    "Why, it's Mr Clathermont" -> Discard,                                  //6 Outlandish Copy - 4 hours
    "An Undignified Ruckus" -> Discard,                                     //6 Outlandish Copy - 2 hours
    "A Gemstone to Shame Rajahs" -> Discard,                                //3 of each - requires friend with Light Fingers
    "They Want to Hear of the Vake" -> Discard,                             //3 of each - requires friend with Bag A Legend
    "Baying for Blood" -> Discard                                           //3 of each - requires friend with Nemesis
  )
  
  private val affairOfTheBox = Map(
    "The Acacia and the Butterfly" -> DiscardUnless(c => c.qualities("A Boxful of Intrigue") < 13, c => {
      c.chooseBranch("Bring in the neddy men")
    })
  )
  
  private val tournamentOfLilies = Map(
    "Where did that come from?" -> Discard,       //50 rostygold or start the tournament
    "The Noted Orchid-Grower consults" -> Discard //requires fate for small payoff
  )
  
  private val cityVices = Map(
    "City Vices: what profit?" -> Discard,                        //i don't want to sell my soul! at least not cheaply
    "City Vices: an Entanglement with an Old Friend" -> Discard,  //quirk gains cap at 6
    "City Vices: a Rather Decadent Evening" -> DiscardUnless(_.qualities("Connected: Bohemian") > 50), //trades in conn: bohemian for Making Waves based on Scandal
    "City Vices: a tournament of weasels!" -> Discard,                                   //50% chance of 2 smiles, 3cp docks, 5 rostygold
    "City Vices: help the Sardonic Music-Hall Singer" -> Play(implicit c => {
      gear.persuasive()
      c.chooseBranch("Help her negotiate")  //?
    }),
    "City Vices: Ask the Sardonic Music-Hall Singer to help you" -> Play(implicit c => {
      gear.persuasive()
      c.chooseBranch("An invitation to a rather exclusive soirée")  //1.2E of gossip, or rare Aeolian Scream
    })
  )
  
  private val SMEN = Map(
    "Pass the Cat: a wriggling delivery" -> HoldUntil(_.scandal > 0, _.chooseBranch("An elaborate strategy")), //-scandal, can get someone a cat box
    "The Northbound Parliamentarian" -> Discard, //3 appalling secrets and WTFC
    "A voice from a well" -> Discard,            //5+cp of nightmares, 2 appalling secrets
    "An ivied wall" -> Hold,                     //+SMEN! -dangerous, -shadowy, +wounds, +suspicion
    "The Body and the Number" -> Hold,
    "The Mind and the Number" -> Hold,
    "The Soul and the Number" -> Hold,
    "A contretemps in a restaurant" -> Hold,     //cannot discard, all options costly or bad
    "Restitution?" -> Hold,                      //cannot discard, "Give to the poor" -50 rostygold +urchins +docks
    "St Arthur's Candle" -> Hold
  )
  
  private val dreams = Map(
    "A dream about the mist" -> Play
  )
  
  val opportunities = new Opportunist(watchful ++ shadowy ++ dangerous ++ persuasive ++
                                      routes ++ lodgings ++ connections ++ conflicts ++ acquaintances ++ items ++ mysteries ++ relickers ++ dreams ++ cityVices ++
                                      affluentPhotographer ++ countingTheDays ++ wilmotsEnd ++ doubtStreet ++ affairOfTheBox ++ tournamentOfLilies ++ SMEN ++ Map(
    "Weather at last" -> Discard,                                                    //quirks up to a point
    "Bringing the revolution" -> Discard,                                            //quirks up to 6 and 1cp shadowy
    "A street-cart is selling Fourth City Rags" -> Discard,                          //i do not rags
    "Ask Madame Shoshana to cast your horoscope" -> Discard,                         //recasting sign requires fate
    "Putting the pieces together: something about the Fourth City" -> Discard,       //low-chance luck challenge, and social
    "A Sporting Sort" -> Discard,                                                    //pure gamble
    "A day at the races" -> Discard,                                                 //fatelocked sidequests area, i've already done it
    "A libraryette for Mr Pages" -> Discard,                                         //req: too much to grind for, for now
    "A day without laudanum" -> Play,                                                //reduces addiction
    "A past benefactor" -> Discard,                                                  //sudden insight
    "Cheesemonger no more" -> DiscardUnless(c => c.qualities("Melancholy") < 9 || c.qualities("Steadfast") < 9 || c.qualities("Ruthless") < 9, c => {                                             
      if (c.qualities("Melancholy") < 9) 
        c.chooseBranch("You regret what happened")
      else if (c.qualities("Steadfast") < 9) 
        c.chooseBranch("You did the right thing")
      else if (c.qualities("Ruthless") < 9) 
        c.chooseBranch("It's all in the game")
    }),                                                                                                                                             
    "Wanted: Reminders of Brighter Days" -> DiscardUnless(_.items("Incendiary Gossip") >= 25, _.chooseBranch("The tiniest of classified advertisements")),
    "Mr Wines is holding a sale!" -> HoldUntil(_.items("Romantic Notion") >= 80, _.chooseBranch("A discount for purchase in bulk")),
    "A night on the tiles" -> DiscardUnless(_.items("Greyfields 1868 First Sporing") > 0, _.chooseBranch("A bottle of the '68")),                     //1E of influence
    "Swap Incendiary Gossip" -> DiscardUnless(c => c.items("Incendiary Gossip") > 0 && c.qualities("Connected: Society") >= 50, _.chooseBranch()),
    "The Soft-Hearted Widow" -> DiscardUnless(_.items("Glim") >= 2500, _.chooseBranch("Give a significant donation to her charity for the homeless")), //upgrades to 2x stolen kiss, +making waves
    "A presumptuous little opportunity" -> HoldUntil(
      c => c.items("Greyfields 1882") >= 1000 || 
      c.items("Morelways 1872") >= 400 || 
      c.items("Greyfields 1879") >= 5000 || 
      c.items("Cellar of Wine") >= 5 ||
      c.items("Fourth City Airag: Year of the Tortoise") >= 7,
      c => if (c.items("Fourth City Airag: Year of the Tortoise") >= 7) {
        c.chooseBranch("A potent possibility")
      } else if (c.items("Cellar of Wine") >= 5) {
        c.chooseBranch("An improbable exchange")
      } else if (c.items("Greyfields 1879") >= 5000) {
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
    "Back to the Palace cellars" -> HoldUntil(_.items("Drop of Prisoner's Honey") >= 100, implicit c => {
      gear.watchful()
      c.chooseBranch("Return with a gift")                                              //net 25 clues, 1 appalling, and 1 TOT - 1.15E
    }),
    "Miniature mausoleums" -> Discard,                                                  //arguably Watchful. probably specific to Palace. "Examine the inscriptions" gives 24 clues, "A spot of grave-robbery" 4 Relics (0.6E)
    "Stealth watch repair" -> Play("It's beneath his dignity, but..."),                 //55 brass, 25 pearls, 1 flawed diamond, 1 sapphire - 1.04E
    "A merry gentleman" -> Play("Ignore the Merry Gentleman"),                          //50% to reduce nightmares
    "Give a Gift! A commotion in the Square of Lofty Words" -> DiscardUnless(_.qualities("Hedonist") >= 5, _.chooseBranch("'I myself am my only true friend!'")), //second chances
    "2000 Foxfire Candles wanted! Will pay handsomely!" -> Discard,                     //no net loss but costly to grind
    "A Polite Invitation" -> Discard,                                                   //party is fun but been there done that
    "Invited to another revel of the Masters" -> Discard,                               //for conn:masters
    "A visit from Slowcake's Amanuensis" -> Hold,                                       //for Notability
    "I've brought something for you to try, dear" -> Discard,                           //statistically costs actions and wounds, occasional action boost
    "The Departed" -> Discard                                                           //rats
  ))
}
