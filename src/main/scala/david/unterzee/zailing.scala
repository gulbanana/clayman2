package david.unterzee
import api._
import common._
import david.gear

/**
 * The basic goal here is to get Approaching Journey's End to 9 before Troubled Waters gets too high
 * This does not take into account fury of the unterzee since i've never seen it reached..
 */
object zailing {  
  private val justBad = Map(
    "Share your Research with a Fellow Scholar" -> Unplayable, //social
    "A Mountain of the Unterzee" -> Unplayable,                //-2/+2 or +2/-2, random
    "Those engines don't sound healthy" -> Unplayable,         //either -1 journey -1 troubled, or a bundle
    "She's Going Down!" -> Unplayable,                         //-journey, and quirks
    "The Killing Wind" -> Unplayable,                          //statistically Bad with no zub
    "Calm Seas: Creaking from Above" -> Unplayable,            //always troubled, 50% journey
    "Lashing Waves: A Blanket of Fog" -> Unplayable            //50% +2 troubled,
  )
  
  private val lucrative = Map(
    "Zailing in style" -> Trivial,
    "Calm Seas: A Steamer full of Passengers" -> Playable(_.chooseBranch("Invite them aboard for a party"))
  )
  
  private val betterThanBold = Map(
    "A Wily Zailor" -> Trivial,                                                                                                                      //XXX gets better with exzperience, so change this after more voyagez
    "The Clinging Coral Mass" -> Playable(implicit c => {gear.persuasive(); c.chooseBranch("'Put your backs into it, lads!'")}),                     //+2 journey
    "The Fleet of Truth" -> Playable(implicit c => {gear.dangerous(); c.chooseBranch("Villainy!")}),                                                 //+journey (XXX check this), +5 of each type of notes
    "Calm Seas: Fair Zailing" -> Trivial,                                                                                                            //+4 journey, +3 troubled, +SIC
    "Calm Seas: A Spit of Land" -> Playable(_.chooseBranch("Steam on by")),                                                                          //+2 journey, +2 troubled
    "Calm Seas: A Huge Terrible Beast of the Unterzee!" -> Playable(implicit c => {gear.dangerous(); c.chooseBranch("Delicious, delicious lumps")})  //+2 journey (XXX check this), +SIC
  )
  
  private val betterThanPrudent = Map(
    "A Corvette of Her Majesty's Navy" -> Conditional(_.qualities("Suspicion") < 5, _.chooseBranch("Exchange pleasantries via semaphore")), //+1 journey, -1 troubled
    "Lashing Waves: A Stowaway!" -> Playable(_.chooseBranch("Let him off at the next port")),                                               //+1 journey
    "Lashing Waves: A Tiny Coral Island" -> Playable(_.chooseBranch("Record it and move on")),                                              //+1 journey, +1 troubled - better with zub
    "Lashing Waves: A Ship of Zealots" -> Playable(implicit c => {gear.dangerous(); c.chooseBranch("See them off")})                        //+1 journey, +1 troubled?
  )
  
  private val allCards = justBad ++ lucrative ++ betterThanBold ++ betterThanPrudent 
  
  private val portCards = lucrative
  private val calmCards = lucrative ++ betterThanBold
  private val troubledCards = lucrative ++ betterThanBold ++ betterThanPrudent
  
  lazy val opportunities_port = new Opportunist(portCards withDefault(_ => Unplayable), allCards.keySet -- portCards.keySet)
  lazy val opportunities_calm = new Opportunist(calmCards withDefault(_ => Unplayable), allCards.keySet -- calmCards.keySet)
  lazy val opportunities_troubled = new Opportunist(troubledCards withDefault(_ => Unplayable), allCards.keySet -- troubledCards.keySet)
  
  //+1 journey, +2 troubled
  def steam_prudently()(implicit c: Character) {
    c.beginStorylet("Steam Prudently")  
    c.chooseBranch()
  }
  
  //50% +2 journey & +1 troubled; 50% +1 journey & +3 troubled
  //requires troubled < 9
  def steam_boldly()(implicit c: Character) {
    c.beginStorylet("Steam Boldly")  
    c.chooseBranch("Extrapolate from the charts") 
  }
}