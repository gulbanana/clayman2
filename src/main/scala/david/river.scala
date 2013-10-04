package david
import api._
import common._

object river { 
  private def blacklist = Set(
    "How much can you see of the far bank?"  -> Discard, //+wounds, +nightmares, 3 appalling secrets
    "...or you could just give up." -> Discard, //+3cp, -nightmares, lots of GOTG
    "Looking upwards" -> Discard, //social
    "Stare at the shore of the living world" -> Discard, //-1cp
    "Recall scenes from Ladybones Road" -> Discard, //-1cp
    "Recall the noise and life of Spite" -> Discard, //-1cp
    "Recall the rough camaraderie of Watchmaker's Hill" -> Discard, //-1cp
    "Recall the glitter of the Shuttered Palace" -> Discard, //-1cp
    "Remember the Flit" -> Discard, //-1cp
    "Remember where you fell" -> Discard //-1cp
  )
  
  private def playlist = Map(
    "You remember the tomb-colonists, and shudder" -> Play, //-2cp
    "Trail your fingers in the water" -> Play,              //-2cp
    "Pilfer a few breaths from another passenger" -> Play  //-2cp 
  )
  
  val opportunities = new Opportunist(playlist ++ blacklist)
  
  def reduce_wounds()(implicit c: Character) = {
    gear.watchful()
    c.beginStorylet("Play chess with the Boatman")
    c.chooseBranch("He's done this before...") //-1cp, +watchful
    c.onwards()
  }
}
