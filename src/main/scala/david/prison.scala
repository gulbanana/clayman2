package david
import london._
import common._

object prison { 
  private def blacklist = Set[String](
    "Voice from the oubliette",
    "Promises of revenge", //only 1 cp
    "University of crime", //+2cp shadowy but costs 2cp criminals and +1 suspicion
    "Volunteer for bilge duty", //-persuasive, -shadowy
    "The talkative gaoler", //only useful if you lose all criminals
    "The screaming prisoner" //only 1 cp
  )
  
  private def playlist = Map[String,Opportunity](
    "The Warden" -> Trivial,
    "A visit from a priest" -> Playable(_.chooseBranch("Admit nothing"))  //+3cp criminals, docks
  ) withDefaultValue Unplayable
  
  val opportunities = new Opportunist(playlist, blacklist)
  
  def reduce_suspicion()(implicit c: Character) = {
    c.equip("Workman's Clothes")
    if (c.items("Greyfields 1879") > 5000) {
      c.beginStorylet("Bribery")
      c.chooseBranch("Arrange for some booze")
    } else if (c.items("Foxfire Candle Stub") > 2000) {
      c.beginStorylet("Bribery")
      c.chooseBranch("Candles for the gaolers")
    } else {
      c.beginStorylet("Prison life")
      c.chooseBranch("Observing New Newgate")
    }
  }
}
