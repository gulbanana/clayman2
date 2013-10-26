package david
import api._
import common._

// XXX unfinished
object marches { 
  private val blacklist = Map(
    "Voice from the oubliette"  -> Discard
  )
  
  private val playlist = Map(
    "The Warden" -> Play
  )
  
  private val escape = Map(
    "The new cell" -> Play             //gain dirigible schedule
  )
  
  val opportunities = new Opportunist(blacklist ++ playlist ++ escape)
  
  def reduce_menace()(implicit c: Character) = {
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
