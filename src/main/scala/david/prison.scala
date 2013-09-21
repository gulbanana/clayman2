package david
import api._
import common._

object prison { 
  private def blacklist = Set(
    "Voice from the oubliette"  -> Discard,
    "A Better Class of Villain" -> Discard, //social
    "Promises of revenge"       -> Discard, //only 1 cp
    "University of crime"       -> Discard, //+2cp shadowy but costs 2cp criminals and +1 suspicion
    "Volunteer for bilge duty"  -> Discard, //-persuasive, -shadowy
    "The talkative gaoler"      -> Discard, //only useful if you lose all criminals
    "The screaming prisoner"    -> Discard, //only 1 cp
    "Sent to the treadmill"     -> Discard, //-shadowy
    "Starvation day"            -> Discard  //+crim, +docks, -2 suspicion, -ALL MAIN QUALITIES - maybe use it if stat capped?
  )
  
  private def playlist = Map(
    "The Warden" -> Play,
    "Start a brawl" -> Play,
    "A visit from a priest" -> Play(_.chooseBranch("Admit nothing")),  //+3cp criminals, docks
    "The Stuttering Fence" -> Play(_.chooseBranch("Connections"))      //-criminals, -suspicion
  )
  
  val opportunities = new Opportunist(playlist ++ blacklist)
  
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
