package david.unterzee
import api._
import common._
import david.gear

/**
 * The basic goal here is to get Approaching Journey's End to 9 before Troubled Waters gets too high
 * This does not take into account fury of the unterzee since i've never seen it reached..
 * 
 * The card maps cover both the Broad Unterzee (southern archipelago) and the Sea of Voices. Inefficient,
 * but there are some common cards and no differing-meaning cards.
 * journey 4+1, troubled 4+1
 * Conversion rate for notes pages:
 * 500 = 5x extraordinary implication, 50x incendiary gossip, 5x broken giant, 1x letter of introduction
 *     = 12.5 + 25 + 12.5 = 50E. So they're worth 0.10 each, comparable to tier 2 items. 
 */
object zailing {  
  private val justBad = Map(
    "Share your Research with a Fellow Scholar" -> Discard, //social
    "A Mountain of the Unterzee" -> Discard,                //-2/+2 or +2/-2, random
    "Those engines don't sound healthy" -> Discard,         //either -1 journey -1 troubled, or a bundle
    "She's Going Down!" -> Discard,                         //-journey, and quirks
    "The Killing Wind" -> Discard,                          //statistically Bad with no zub
    "A Good Meal" -> Discard,                               //9 pages of notes - 0.9E
    "Calm Seas: Creaking from Above" -> Discard,            //always troubled, 50% journey
    "Lashing Waves: A Blanket of Fog" -> Discard            //50% +2 troubled, 50% ?
  )
  
  private val lucrative = Map(
    "Zailing in style" -> Play,                                                                          //many echoesworth of stuff
    "The Fleet of Truth" -> Play(implicit c => {gear.dangerous(); c.chooseBranch("Villainy!")}),        //+journey (XXX check this), 15 pages of notes - 1.5E
    "Calm Seas: A Steamer full of Passengers" -> Play(_.chooseBranch("Invite them aboard for a party")) //1.4E of things, hedonist, and scandal
  )
  
  private val betterThanBold = Map(
    "A Wily Zailor" -> Play,                                                                                                                      //XXX gets better with exzperience, so change this after more voyagez
    "The Clinging Coral Mass" -> Play(implicit c => {gear.persuasive(); c.chooseBranch("'Put your backs into it, lads!'")}),                     //+2 journey
    "A Light in the Fog" -> Play(_.chooseBranch("Keep away from the lighthouse")),                                                               //+2 journey
    "Listen to the Wind" -> Play(_.chooseBranch("Steam the way the voices tell you")),                                                           //+3 journey, +3 troubled
    "Crossing Paths" -> Play(_.chooseBranch("Steam on by")),                                                                                     //+2 journey, +3 troubled
    "Calm Seas: Fair Zailing" -> Play,                                                                                                            //+4 journey, +3 troubled, +SIC
    "Calm Seas: A Spit of Land" -> Play(_.chooseBranch("Steam on by")),                                                                          //+2 journey, +2 troubled
    "Calm Seas: A Huge Terrible Beast of the Unterzee!" -> Play(implicit c => {gear.dangerous(); c.chooseBranch("Delicious, delicious lumps")}), //+3 journey, secrets, +SIC
    "Calm Seas: Meeting a Local Steamer" -> Play(_.chooseBranch("I say, must you do that?")),                                                    //+2 journey, -1 troubled, +1 ztory
    "Calm Seas: The Giant of the Unterzee" -> Play(implicit c => {gear.persuasive(); c.chooseBranch()}),                                         //+2 journey
    "Lashing Waves: A Ship of Zealots" -> Play(implicit c => {gear.dangerous(); c.chooseBranch("See them off")})                                 //+2 journey, +1 troubled; fail: +3 troubled
  )
  
  private val betterThanPrudent = Map(
    "A Corvette of Her Majesty's Navy" -> DiscardUnless(_.suspicion < 5, _.chooseBranch("Exchange pleasantries via semaphore")), //+1 journey, -1 troubled 
    "A Hazard to Shipping" -> Play(implicit c => {gear.watchful(); c.chooseBranch()}),                                                  //+? journey (1 or 2)
    "Lashing Waves: A Stowaway!" -> Play(_.chooseBranch("Let him off at the next port")),                                               //+1 journey
    "Lashing Waves: A Tiny Coral Island" -> Play(_.chooseBranch("Record it and move on")),                                              //+1 journey, +1 troubled - better with zub
    "Lashing Waves: A Shape in the Depths" -> Play(implicit c => {gear.watchful(); c.chooseBranch("Take a closer look")})               //+? journey, +? troubled
  )
  
  private val betterThanNothing = Map(
    "Fury of the Unterzee: The Stone Pigs Cough" -> Discard,
    "Fury of the Unterzee: A Blank Space on the Charts" -> Discard, //50% lose all progress and waters, 50% lose some waters
    "Fury of the Unterzee: Hardship and Want" -> Play(_.chooseBranch("Let them eat pies")), //yacht yeah!
    "Fury of the Unterzee: cold, calm waters" -> Play(implicit c => {gear.dangerous(); c.chooseBranch()}) //plated seal
  )
  
  private val allCards = justBad ++ lucrative ++ betterThanBold ++ betterThanPrudent 
  
  private val portCards = lucrative
  private val calmCards = lucrative ++ betterThanBold
  private val troubledCards = lucrative ++ betterThanBold ++ betterThanPrudent
  private val furyCards = lucrative ++ betterThanBold ++ betterThanPrudent ++ betterThanNothing
  
  private def discardOtherThan(chosenCards: Map[String,Opportunity]) = allCards -- chosenCards.keySet
  
  lazy val opportunities_port = new Opportunist(portCards ++ discardOtherThan(portCards))
  lazy val opportunities_calm = new Opportunist(calmCards ++ discardOtherThan(calmCards))
  lazy val opportunities_troubled = new Opportunist(troubledCards ++ discardOtherThan(troubledCards))
  lazy val opportunities_fury = new Opportunist(furyCards ++ discardOtherThan(furyCards))
  
  //southern archipelago - +1 journey, +2 troubled
  def archipelago_safe()(implicit c: Character) {
    c.beginStorylet("Steam Prudently")  
    c.chooseBranch()
    c.onwards()
  }
  
  //sea of voices - +1 journey, +2 troubled
  def voices_safe()(implicit c: Character) {
    c.beginStorylet("Steam Carefully")  
    c.chooseBranch()
    c.onwards()
  }
  
  //southern archipelago - 50% +2 journey & +1 troubled; 50% +1 journey & +3 troubled; requires troubled < 9
  def archipelago_fast()(implicit c: Character) {
    c.beginStorylet("Steam Boldly")  
    c.chooseBranch("Extrapolate from the charts")
    c.onwards()
  }
  
  //sea of voices - 50% +2 journey & +1 troubled; 50% +1 journey & +3 troubled; requires troubled < 9
  def voices_fast()(implicit c: Character) {
    c.beginStorylet("Steam Bravely")  
    c.chooseBranch("Extrapolate from the charts")
    c.onwards()
  }
}