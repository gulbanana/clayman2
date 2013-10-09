package hors
import common._

object cards {
  val standard = Map(
    "An intriguing rumour" -> Discard,                                                      //watchful 0
    "Cleaning up the neighbourhood" -> Discard,                                             //watchful 9
    "An unsolved riddle" -> Discard,                                                        //watchful 9
    "Divining the dirigibles" -> Discard,                                                   //watchful 21
    "The devil's parrot" -> Discard,                                                        //watchful 21
    "Panning the Unterzee" -> Discard,                                                      //watchful 21
    "Night duty at Concord Square" -> Discard,                                              //watchful 21
    "The secrets of rats" -> Discard,                                                       //watchful 21
    "Work the carnival" -> Discard,                                                         //shadowy 45
    "As silent as the grave?" -> Discard,                                                   //shadowy 45
    "Rob a Brass Embassy courier" -> Discard,                                               //shadowy 45
    "Rob a library at the University" -> Discard,                                           //shadowy 45
    "Academic discipline" -> Discard,                                                       //dangerous 45
    "A night at the carnival" -> Discard,                                                   //dangerous 45
    "Minding the detective" -> Discard,                                                     //dangerous 45
    "Moonish water" -> Discard,                                                             //dangerous 57
    "His Young Lordship seized by tentacles" -> Discard,                                    //dangerous 57
    "What's in the sack, Jack?" -> Discard,                                                 //dangerous 57
    "Ambushed by pirates!" -> Discard,                                                      //dangerous 57
    "Debate with a philosopher at the Square of Lofty Words" -> Discard,                    //persuasive 21
    "Journal requires original poetry for publication" -> Discard,                          //persuasive 21
    "Ghostwrite a tongue-tied lover's poetry" -> Discard,                                   //persuasive 21
    "Win your way into a dying widow's will" -> Discard,                                    //persuasive 21
    "Trouble at home" -> Discard,                                                           //persuasive 21
    "Moving in rarefied circles" -> Discard,                                                //persuasive 21
    "After dinner speaking" -> Discard,                                                     //persuasive 33
    "Our forthright reporter" -> Discard,                                                   //persuasive 33
    "Weather at last" -> Discard,                                                           //free of surface ties
    "The Northbound Parliamentarian" -> Discard,                                            //free of surface ties
    "Eavesdropping in the gardens" -> Discard,                                              //Cobblestone Rogues and Back-Alley Saints
    "The Windward Tower" -> Discard,                                                        //lodgings (100 TOT)
    "The Tower of Sparrows" -> DiscardUnless(_.items("Incendiary Gossip") >= 10),           //lodgings
    "The Sleepless Tower" -> DiscardUnless(_.items("Mystery of the Elder Continent") >= 8), //lodgings
    "The Heron Tower" -> Play(_.chooseBranch("Hunt down a huge lizard")),                   //lodgings
    "A visit" -> Discard,                                                                   //acq
    "Mr Wines is holding a sale!" -> Discard,
    "The Curt Relicker and Montgomery are Moving Quietly Past" -> Discard,  
    "The Coquettish Relicker and Mathilde are Making the Rounds" -> Discard,
    "The Ways of Wolfstack Docks" -> Discard,
    "The Ways of the Flit" -> Discard,
    "The Ways of the Shuttered Palace" -> Discard,
    "The Ways of the Labyrinth of Tigers" -> Discard,
    "What will you do with your Bandaged Raven?" -> Discard,
    "Altars and alms-houses: the Church" -> Discard,                       
    "The Alleys of London: the Criminals" -> Discard,
    "Gunpowder and Zeal: the Revolutionaries" -> Discard,
    "Burning Shadows: the Devils of London" -> Discard,
    "Court and Cell: the Constables" -> Discard,
    "Bandages and Dust: The Tomb-Colonies" -> Discard,
    "The Demi-Monde: Bohemians" -> Discard,
    "The Roof-Tops: Urchins" -> Discard,
    "Whispers from the Surface: The Great Game" -> Discard,
    "An implausible penance" -> Discard,
    "An opportunity for profit" -> Discard
  )
  
  val dreams = Map(
    //death by water
    "A dream about whispers" -> Play,
    "A dream about descending into darkness." -> Play,
    "A dream about the hold of a ship in a storm" -> Play,
    //is someone there
    "A dream about a window at night" -> Play,
    "A dream about a corridor lined with brass mirrors." -> Play,
    //what the thunder said
    "A dream about the mist" -> Play,
    "A dream about stormy weather" -> Play,
    "A dream about a familiar face" -> Play(_.chooseBranch("A familiar, paint-splattered figure")),
    "A dream about a cave" -> Play,
    //burial of the dead
    "A dream about a town square" -> Play
  )
  
  val story = Map(
    "The Cities that Fell" -> Discard,
    "The Seekers of the Garden" -> Discard,
    "Investigating the Affluent Photographer" -> Discard,
    "A past benefactor" -> Discard,
    "A day at the races" -> Discard,
    "A Sporting Sort" -> Discard,                                                           //random chance
    "An afternoon of good deeds?" -> Play(_.chooseBranch("Quite a moral afternoon.")),      //counting the days
    "The Numismatrix" -> Discard,                                                           //counting the days
    "Graffiti with a sting" -> Discard,                                                     //counting the days
    "An unusual wager" -> Discard,                                                          //counting the days
    "A Moment's Peace" -> Play(_.chooseBranch("Relax and enjoy")),                          //counting the days
    "The independence of young ladies" -> Play,                                             //clathermont 1
    "A burglary of tattoos" -> Play,                                                        //clathermont 1
    "Tattoos and extortion" -> Play,                                                        //clathermont 1
    "Early closing" -> Play,                                                                //clathermont 5
    "Something going on at Clathermont's" -> Play,                                          //clathermont 5
    "Aunt amok!" -> Play,                                                                   //aunt 1
    "Who knows the Cheesemonger?" -> Discard,                                               //cheesemonger 2
    "What do the urchins know?" -> Discard,                                                 //cheesemonger 2
    "The Cheesemonger?" -> Discard,                                                         //cheesemonger 2
    "A commission from anarchists" -> Discard,                                              //plotting 0
    "A stranded shipment" -> Hold,                                                          //broken toys 3
    "The wine cellars of the Palace" -> Discard,                                            //needs route
    "Where did that come from?" -> Discard,                                                 //tournament of lilies 0
    "The shipment arrives tomorrow!" -> Hold,                                               //in search of a stiff drink 4
    "Searching for the Secular Missionary's husband" -> Play(_.chooseBranch("Take her to the Forgotten Quarter")), //secular missionary 2
    "Revolution and the Firebrand" -> Play(_.chooseBranch(" Invite him to your lodgings")), //revolutionary firebrand 3
    "Another note from Mr Iron" -> Play                                                     //umpire 1
  )
    
  val social = Map(
    "Wanted: Reminders of Brighter Days" -> Discard,                                
    "Pass the Cat: a wriggling delivery" -> Hold,
    "Give a Gift! A commotion in the Square of Lofty Words" -> Hold
  )
}

object opportunities extends Opportunist(cards.standard ++ cards.story ++ cards.social ++ cards.dreams) {
}