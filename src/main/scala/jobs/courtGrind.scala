package jobs
import london._
import common._

//hang around the court grinding
object courtGrind extends OneManJob {
  def apply(implicit c: Character) = repeat {
    if (opportunities.can_act())
      opportunities.act_once()
    else if (c.location != Areas.Court)
      getToCourt(c)
    else
      atCourt(c)
  }
  
  private def getToCourt(implicit c: Character) {
    gear.persuasive()
    
    if (c.qualities("Connected: the Duchess") < 10)
      connected.the_duchess()
      
    else {
      c.travel(Areas.ShutteredPalace)
      c.beginStorylet("Spend a few days at Court")
      c.chooseBranch("A word from the Duchess")
      c.onwards()
    }
  }
  
  private def atCourt(implicit c: Character) {
    gear.persuasive()
    
    //use up Fascinating, if available
    if (c.qualities("Scandal") > 0 && c.qualities("Fascinating...") >= 4)
      court.fix_scandal()
    else if (c.qualities("Wounds") > 0 && c.qualities("Fascinating...") >= 5)
      court.fix_wounds()
      
    //prioritise grinds that gain Fascinating up to a useful level
    else if (c.items("Whispered Secret") < 80000) //buy the lofty tower
      court.grind_secrets()
    else if (c.qualities("Fascinating...") < 5)
      court.grind_clues()

    //if we've already got Fascinating:
    else if (c.items("Prisoner's Honey") < 40000) //buy the royal bethlehem
      court.grind_honey()
    else if(c.qualities("Scandal") < 7)
      court.grind_influence()
    
    //safe money fallback
    else
      court.grind_jade()
  }
  
  private def wearCourtOutfit(implicit c: Character) {
    
  }
}