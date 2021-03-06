package david
import api._
import common._

object colonies extends MenaceArea { 
  private def blacklist = Map(
    "Musings on the Causes of Exile" -> Discard  //social
  )
  
  private def playlist = Map(
    "A letter from the Comte" -> Play,
    "'..I have attended a ball...'" -> Play,
    "A friend of sorts" -> Play,
    "Lamentable tastes" -> Play,
    "The construction of the 'Grand Sanatoria'" -> Play,
    "I Met a Curious Creature" -> Play                   //Gain a tomb-lion
  )
  
  val opportunities = new Opportunist(playlist ++ blacklist)
  
  private val usefulConnections = Map(
    "Connected: The Church" -> "'...I have written to the Bishop of St Fiacre's...'",
    "Connected: Society" -> "'....I have a few friends yet in Society...",
    "Connected: the Duchess" -> "'...I flatter myself the Duchess was fond of me...'"
  )
  
  def reduce_menace()(implicit c: Character) = {
    c.beginStorylet("A letter to an old flame")
    c.chooseBranch(usefulConnections(usefulConnections.keySet.max(Ordering.by(c.qualities))))
  }
  
  val exitStorylets = Set("Home. At last.")
}
