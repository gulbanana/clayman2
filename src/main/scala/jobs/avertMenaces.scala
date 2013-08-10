package jobs
import london._
import common._
import david._

//eat all opportunties and actions until we run out of one or the other
object avertMenaces extends RepeatedJob {
  def apply(implicit c: Character) = escapePrison || reduceMenaces
  
  //there's no good repeatable way to reduce suspicion, so just treat prison as a revolving door
  def escapePrison(implicit c: Character) = did (c.location == Areas.NewNewgate) {
    prison.opportunities.played() or prison.reduce_suspicion()
  }
  
  def reduceMenaces(implicit c: Character) = did (c.qualities("Scandal") > 4) { //this depends on luck, so start it ASAP
    c.travel(Areas.Lodgings)
    c.beginStorylet("Attend a Church Service")
    c.chooseBranch()
    c.onwards()
  } or (c.qualities("Nightmares") > 5) {  //may increase scandal, so it comes after
    c.travel(Areas.Carnival)
    if (c.items("Carnival Ticket") < 1) {
      c.beginStorylet("Buy tickets to the Carnival")
      c.chooseBranch("Buy a ticket with moon-pearls")      
    } else {
      c.beginStorylet("The Refreshment Pavilion")
      c.chooseBranch("Try some hot wine")
    }
    c.onwards()
  } or (c.qualities("Wounds") > 5) {  //reconsider this.. it might be faster just to die
    c.travel(Areas.Lodgings)
    c.beginStorylet("Time in bed")
    val bestSleep = (c.branches - "A remarkable tincture").toSeq.sortBy(guestPriorities).head
    c.chooseBranch(bestSleep)
    c.onwards()
  }
  
  private val guestPriorities = Map(
    "Visions in the mirror" -> +1,  //nightmares on failure!
    "A visit from the Wry Functionary" -> -1,
    "A visit from the Repentant Forger" -> -1,
    "A visit from the Sardonic Music-Hall Singer" -> -1,
    "A visit from the Regretful Soldier" -> -1,
    "A dreamless sleep" -> -2  //7cp
  ).withDefault(_ => 0)

}