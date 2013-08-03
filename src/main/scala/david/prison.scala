package david
import london._
import common._

object prison { 
  private def blacklist = Set[String]()
  
  private def playlist = Map[String,Opportunity]() withDefaultValue Unplayable
  
  val opportunities = new Opportunist(playlist, blacklist)
  
  def reduce_suspicion()(implicit c: Character) = {
    c.equip("Workman's Clothes")
    c.beginStorylet("Prison life")
    c.chooseBranch("Observing New Newgate")
  }
}
