package david
import api._
import common._

object prison { 
  private def blacklist = Set(
    "Voice from the oubliette",
    "A Better Class of Villain", //social
    "Promises of revenge",       //only 1 cp
    "University of crime",       //+2cp shadowy but costs 2cp criminals and +1 suspicion
    "Volunteer for bilge duty",  //-persuasive, -shadowy
    "The talkative gaoler",      //only useful if you lose all criminals
    "The screaming prisoner",    //only 1 cp
    "Sent to the treadmill"      //-shadowy
  )
  
  private def playlist = Map(
    "The Warden" -> Play,
    "Start a brawl" -> Play,
    "A visit from a priest" -> Play(_.chooseBranch("Admit nothing")),  //+3cp criminals, docks
    "The Stuttering Fence" -> Play(_.chooseBranch("Connections"))      //-criminals, -suspicion
  ) withDefaultValue Hold
  
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
