package david
import london._

object court {
  //+83 whispered secrets, +fascinating
  //failure: -fascinating
  def grind_mysteries()(implicit c: Character) {
    c.beginStorylet("Attend to matters of romance")
    c.chooseBranch("Attend courtly functions")
    c.onwards()
  }
  
  //+4 morelways, +fascinating
  //failure: -fascinating
  def grind_morelways()(implicit c: Character) {
    c.beginStorylet("Attend to matters of romance")
    c.chooseBranch("Perform artistically")
    c.onwards()
  }
  
  //+44 cryptic clues, +fascinating
  //failure: -fascinating
  def grind_clues()(implicit c: Character) {
    c.beginStorylet("Attend to matters of romance")
    c.chooseBranch("Take a stroll in the gardens")
    c.onwards()
  }
  
  //+91 jade, +hedonist
  //failure: -hedonist
  def grind_jade()(implicit c: Character) {
    c.beginStorylet("The Corruption of Youth")
    c.chooseBranch("The seduction of music")
    c.onwards()
  }
  
  //+45 honey, +hedonist
  //failure: -hedonist
  def grind_honey()(implicit c: Character) {
    c.beginStorylet("The Corruption of Youth")
    c.chooseBranch("Join a picnic expedition")
    c.onwards()
  }
  
  //+18 stolen correspondence, connected: society
  //failure: 2cp scandal
  def grind_influence()(implicit c: Character) {
    c.beginStorylet("Attend to matters of romance")
    c.chooseBranch("Write a letter")
    c.onwards()
  }
  
  //+20 proscribed material, +2 honey, making waves
  //failure: ?cp scandal
  def grind_rumour()(implicit c: Character) {
    c.beginStorylet("The life of the mind")
    c.chooseBranch("Discuss politics at a salon")
    c.onwards()
  }
  
  //-2cp
  def fix_scandal()(implicit c: Character) {
    if (!c.storylets.contains("Disporting with the servantry")) {
      grind_mysteries()
    } else {
      c.beginStorylet("Disporting with the servantry")
      c.chooseBranch("Catch the eye of a butler")
      c.onwards()
    }
  }
  
  //-2 to -3 cp
  def fix_wounds()(implicit c: Character) {
    if (!c.storylets.contains("Disporting with the servantry")) {
      grind_mysteries()
    } else {
      c.beginStorylet("Disporting with the servantry")
      c.chooseBranch("Make overtures to a cook")
      c.onwards()
    }
  }
}