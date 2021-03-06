package david.london
import api._
import common._
import david._

//hang around the court grinding
object courtGrind extends BufferedJob {
  def apply(implicit c: Character) = {
    gear.persuasive()
    playCards.apply || getToCourt || reduceMenaces || revelsPrep || gainFascinating || grind
  }
  
  private def getToCourt(implicit c: Character) = did (c.location != Areas.Court) {
    if (c.qualities("Connected: Society") > 50) {
      c.travel(Areas.ShutteredPalace)
      c.beginStorylet("Spend a few days at Court")
      c.chooseBranch("A word from your friends at Court")
      c.onwards()      
    } else if (c.qualities("Connected: the Duchess") < 10) {
      connected.the_duchess()
    } else {
      c.travel(Areas.ShutteredPalace)
      c.beginStorylet("Spend a few days at Court")
      c.chooseBranch("A word from the Duchess")
      c.onwards()
    }
  }
  
  private def reduceMenaces(implicit c: Character) = did (c.scandal > 0 && c.qualities("Fascinating...") >= 4) {    
    court.fix_scandal()
  } or (c.wounds > 0 && c.qualities("Fascinating...") >= 5) {
    court.fix_wounds()
  }
  
  private def gainFascinating(implicit c: Character) = did (c.items("Whispered Secret") < 80000) { 
    court.grind_mysteries() //buy the lofty tower
  } or (c.qualities("Fascinating...") < 5) {
    court.grind_morelways()
  }
  
  private def grind(implicit c: Character) = did (c.items("Drop of Prisoner's Honey") < 40000) { 
    court.grind_honey()     //buy the royal bethlehem
  } or (c.items("Proscribed Material") < 15000) {
    court.grind_rumour() 	  //buy the western tower (by converting to souls)
  } or (c.scandal < 7) {
    court.grind_influence() 
  } or {
    court.grind_jade()      //safe money fallback - should never be reached, though
  }
  
  private def revelsPrep(implicit c: Character) = did (c.items("Drop of Prisoner's Honey") < 500) {
    court.grind_honey()
  }
}