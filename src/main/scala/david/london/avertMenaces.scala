package david.london
import api._
import common._
import david._

object avertMenaces extends RepeatedJob {
  def apply(implicit c: Character) = did (c.scandal > 5) { //this depends on luck, so start it ASAP
    c.travel(Areas.Lodgings)
    c.beginStorylet("Attend a Church Service")
    c.chooseBranch()
    c.onwards()
  } or (c.nightmares > 6) {  //may increase scandal, so it comes after
    c.travel(Areas.Carnival)
    if (c.items("Carnival Ticket") < 1) {
      c.beginStorylet("Buy tickets to the Carnival")
      c.chooseBranch("Buy a ticket with moon-pearls")      
    } else {
      c.beginStorylet("The Refreshment Pavilion")
      c.chooseBranch("Try some hot wine")
    }
    c.onwards()
  } or (c.wounds > 6) {  //reconsider this.. it might be faster just to die
    c.travel(Areas.Lodgings)
    c.beginStorylet("Time in bed")
    val bestSleep = (c.branches - "A remarkable tincture").max(Ordering.by(guestPriorities))
    c.chooseBranch(bestSleep)
    c.onwards()
  } //there's no good repeatable way to reduce suspicion, so just treat prison as a revolving door
  
  //this is largely dependent on the airs of london; as they increase in semi-random amounts,
  //the available options improve. it's always a random thing, except for visits from friends 
  //and for Really High airs, which will always heal you a lot
  //The ideal would be to find a low-action way to manipulate Airs before sleeping...
  private val guestPriorities = Map(
    "Visions in the mirror" -> -2,  //nightmares on failure!
    "Spend a day in bed" -> -1, //no healing on failure
    "A visit from the Wry Functionary" -> 1,
    "A visit from the Repentant Forger" -> 1,
    "A visit from the Sardonic Music-Hall Singer" -> 1,
    "A visit from the Regretful Soldier" -> 1,
    "A dreamless sleep" -> 2  //7cp
  ).withDefault(_ => 0)
}