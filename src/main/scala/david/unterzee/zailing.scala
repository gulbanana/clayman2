package david.unterzee
import api._
import common._
import david.gear

/**
 * The basic goal here is to get Approaching Journey's End to 9 before Troubled Waters gets too high
 */
object zailing {
  private def blacklist = Set(
    "Share your Research with a Fellow Scholar", //social
    "A Mountain of the Unterzee",                //-2/+2 or +2/-2, random
    "Those engines don't sound healthy",         //either -1 journey -1 troubled, or a bundle
    "She's Going Down!",                         //-journey, and quirks
    "The Killing Wind",                          //statistically Bad with no zub
    "Lashing Waves: A Blanket of Fog"            //50% +2 troubled,
  )
  
  private def playlist = Map(
    "Zailing in style" -> Trivial,                                                                                                          //$$$
    "The Clinging Coral Mass" -> Playable(implicit c => {gear.persuasive(); c.chooseBranch("'Put your backs into it, lads!'")}),            //+2 journey
    "The Fleet of Truth" -> Playable(implicit c => {gear.dangerous(); c.chooseBranch("Villainy!")}),                                        //+journey, +5 of each type of notes
    "A Corvette of Her Majesty's Navy" -> Conditional(_.qualities("Suspicion") < 5, _.chooseBranch("Exchange pleasantries via semaphore")), //+1 journey, -1 troubled
    "A Wily Zailor" -> Trivial,                                                                                                             //XXX gets better with exzperience, so change this
    "Calm Seas: Fair Zailing" -> Trivial,                                                                                                   //+4 journey, +3 troubled, +SIC
    "Calm Seas: Creaking from Above" -> Trivial,
    "Calm Seas: A Huge Terrible Beast of the Unterzee!" -> Playable(implicit c => {gear.dangerous(); c.chooseBranch("Delicious, delicious lumps")}),  //+journey, +SIC
    "Calm Seas: A Spit of Land" -> Playable(_.chooseBranch("Steam on by")),                                                                 //+2 journey, +2 troubled
    "Calm Seas: A Steamer full of Passengers" -> Playable(_.chooseBranch("Invite them aboard for a party")),                                //+hedonist, 1 stockings, 5 secluded addresses - 1.40E (but +troubled)
    "Lashing Waves: A Stowaway!" -> Playable(_.chooseBranch("Let him off at the next port")),                                               //+1 journey
    "Lashing Waves: A Tiny Coral Island" -> Playable(_.chooseBranch("Record it and move on")),                                              //+1 journey, +1 troubled - better with zub
    "Lashing Waves: A Ship of Zealots" -> Playable(implicit c => {gear.dangerous(); c.chooseBranch("See them off")})                        //+1 journey, +1 troubled?
  ) withDefaultValue Unplayable
  
  val opportunities = new Opportunist(playlist, blacklist)
  
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
  
  def zailed()(implicit c: Character) = did (c.qualities("Troubled Waters") < 9) {
    steam_boldly()
  }
}