package david
import api._
import common._

object river { 
  //never good
  private def blacklist = Map(
    "How much can you see of the far bank?"  -> Discard, //+wounds, +nightmares, 3 appalling secrets
    "...or you could just give up." -> Discard, //+3cp, -nightmares, lots of GOTG
    "Looking upwards" -> Discard //social
  )
  
  //-1cp - not worthwhile compared to chess
  private def m1 = Map(
    "Stare at the shore of the living world" -> Play,
    "Recall scenes from Ladybones Road" -> Play,
    "Recall the noise and life of Spite" -> Play,
    "Recall the rough camaraderie of Watchmaker's Hill" -> Play, 
    "Recall the glitter of the Shuttered Palace" -> Play, 
    "Remember the Flit" -> Play,
    "Remember where you fell" -> Play 
  )
  
  //play these sometimes to avoid raising boatman's opponent too high
  private def m2 = Map(
    "You remember the tomb-colonists, and shudder" -> Play, //-2cp
    "Trail your fingers in the water" -> Play,              //-2cp
    "Pilfer a few breaths from another passenger" -> Play  //-2cp 
  )
  
  val opportunities = new Opportunist(m2, default = Discard)
  
  def reduce_wounds()(implicit c: Character) = {
    gear.watchful()
    c.beginStorylet("Play chess with the Boatman")
    c.chooseBranch() //lv5+, "A smile of recognition": -3cp, +watchful, 100%
    c.onwards()
  }
}
