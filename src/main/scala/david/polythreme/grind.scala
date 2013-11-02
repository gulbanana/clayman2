package david.polythreme
import api._
import common._
import david._

object grind extends RepeatedJob {
  def apply(implicit c: Character) = opportunities.played() || progress || farm
  
  private def progress(implicit c: Character) = false
  
  //+1.2E of notions or silk- better than london, but you have to spend them on progress
  private def farm(implicit c: Character) = {
    c.beginStorylet("One Might Profit Even in Such a Place")
    did (c.items("Romantic Notion") > c.items("Surface-Silk Scrap")) {
      gear.persuasive()
      c.chooseBranch("Soft to the touch")
    } or {
      gear.shadowy()
      c.chooseBranch("Lurk in the eaves")
    }
  }
}