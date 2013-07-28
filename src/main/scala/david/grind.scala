package david
import london._

object grind {
  //+28 soul (0.56E)
  def ladybones_souls()(implicit c: Character) {
    c.travel(Areas.LadybonesRoad)
    c.beginStorylet("Track down a Spirifer")
    c.chooseBranch("Watch from the rooftops")
    c.onwards()
  }
  
  //+54 rostygold with a rare success for appalling secrets
  def ladybones_rostygold()(implicit c: Character) {
    c.travel(Areas.LadybonesRoad)
    c.beginStorylet("Industrial Espionage!")
    c.chooseBranch("Investigate the Embassy warehouses")
    c.onwards()
  }
  
  //+82 jade fragments (0.82E)
  //failure: 2cp scandal
  def quarter_jade()(implicit c: Character) {
    c.travel(Areas.ForgottenQuarter)
    c.beginStorylet("A tour of the quarter")
    c.chooseBranch("The exploitation of knowledge")
    c.onwards()
  } 
}