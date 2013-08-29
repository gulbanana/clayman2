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
    "Those engines don't sound healthy"          //either -1 journey -1 troubled, or a bundle
  )
  
  private def playlist = Map(
    "A Corvette of Her Majesty's Navy" -> Conditional(_.qualities("Suspicion") < 5, _.chooseBranch("Exchange pleasantries via semaphore")), //+1 journey, -1 troubled
    "The Clinging Coral Mass" -> Playable(implicit c => { gear.persuasive(); c.chooseBranch("'Put your backs into it, lads!'")}),           //+2 journey
    "The Fleet of Truth" -> Playable(implicit c => { gear.dangerous(); c.chooseBranch("Villainy!")}),                                       //+journey, +5 of each type of notes
    "Calm Seas: Fair Zailing" -> Trivial,                                                                                                   //+4 journey, +3 troubled, +SIC
    "Calm Seas: A Spit of Land" -> Playable(_.chooseBranch("Steam on by")),                                                                 //+2 journey, +2 troubled
    "Calm Seas: A Steamer full of Passengers" -> Playable(_.chooseBranch("Invite them aboard for a party"))                                 //+hedonist, 1 stockings, 5 secluded addresses - 1.40E (but +troubled)
  ) withDefaultValue Unplayable
  
  val opportunities = new Opportunist(playlist, blacklist)
  
  def steam_prudently(implicit c: Character) {
    c.beginStorylet("Steam Prudently")  //+1 journey, +2 troubled
    c.chooseBranch()
  }
}