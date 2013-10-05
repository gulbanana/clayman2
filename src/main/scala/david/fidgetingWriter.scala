package david
import api._
import common._
import david._

object fidgetingWriter extends OneManJob with Stats {
  def apply(implicit c: Character) = {
    gear.watchful()
    
    //going any higher requires watchful 90
    gamble4 || gamble3 || gamble2 || gamble1
  }
  
  //Tale of Terror!! -> Sense of Déjà Vu
  //gains Touched by Fingerwork
  private def gamble1(implicit c: Character) = did (c.items("Tale of Terror!!") > 0) {
    c.useItem("Tale of Terror!!")
    c.chooseBranch("There's something familiar about this tale...")	//"pretty good" odds
  }

  //Sense of Déjà Vu -> Extraordinary Implication (2.5E)
  private def cashout1(implicit c: Character) = did (c.items("Sense of Déjà Vu") > 0) {
    c.useItem("Sense of Déjà Vu")
    c.chooseBranch("Put the thought aside")
  }
  
  //Sense of Déjà Vu + Vision of the Surface (0.5E) -> Glimpse of Something Larger
  //gains Seeing through the Eyes of Icarus
  private def gamble2(implicit c: Character) = did (c.items("Sense of Déjà Vu") > 0 && c.items("Vision of the Surface") > 0) {
    c.useItem("Sense of Déjà Vu")
    c.chooseBranch("Track down the Fidgeting Writer")	//"pretty good" odds
  }
  
  //Glimpse of Something Larger -> 2x Collated Research (5.0E)
  //gains Approaching the Gates of the Garden
  private def cashout2(implicit c: Character) = did (c.items("Glimpse of Something Larger") > 0) {
    c.useItem("Glimpse of Something Larger")
    c.chooseBranch("Publish a paper on the subject and think no more about it")
  }
  
  //Glimpse of Something Larger + 2x Correspondence Plaque (1.0E) -> Deal with a Devil
  private def gamble3(implicit c: Character) = did (c.items("Glimpse of Something Larger") > 0 && c.items("Correspondence Plaque") >= 2) {
    c.useItem("Glimpse of Something Larger")
    c.chooseBranch("Cancel your appointments and investigate the ideogram")	  //"pretty good" odds
  }
  
  //Deal with a Devil -> Brass Ring (12.5E)
  private def cashout3(implicit c: Character) = did (c.items("Deal with a Devil") > 0) {
    c.useItem("Deal with a Devil")
    c.chooseBranch("Tell him all you know and abandon your research")
  }
  
  //Deal with a Devil + 2x Brilliant Soul (1.0E) -> Room Number at the Royal Beth
  private def gamble4(implicit c: Character) = did (c.items("Deal with a Devil") > 0 && c.items("Brilliant Soul") >= 2) {
    c.useItem("Deal with a Devil")
    c.chooseBranch("Make a counter-offer.")	//"could go either way"
  }
  
  //Room Number at the Royal Beth -> 2x Antique Mystery (25.0E)
  private def cashout4(implicit c: Character) = did (c.items("Room Number at the Royal Beth") > 0) {
    c.useItem("Room Number at the Royal Beth")
    c.chooseBranch("Give him honey and a pen")
  }
  
  //Room Number at the Royal Beth -> Last Hope of a Fidgeting Writer
  private def gamble5(implicit c: Character) = did (c.items("Room Number at the Royal Beth") > 0 && c.items("An Identity Uncovered!") > 0) {
    c.useItem("Room Number at the Royal Beth")
    c.chooseBranch("Visit him regularly and ask the Manager about his stay")	//could go either way
  }
}

object fidgetingWriterStats extends OneManJob with Stats {
  def apply(implicit c: Character) = {
    false
  }
}