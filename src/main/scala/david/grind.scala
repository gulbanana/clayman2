package david
import london._

object grind {  
  /*********/
  /* GOODS */
  /*********/
  //+54 rostygold with a rare success for appalling secrets
  def rostygold()(implicit c: Character) {
    c.travel(Areas.LadybonesRoad)
    c.beginStorylet("Industrial Espionage!")
    c.chooseBranch("Investigate the Embassy warehouses")
    c.onwards()
  }

  /*********/
  /* ELDER */
  /*********/
  //+82 jade fragments (0.82E)
  //failure: 2cp scandal
  def jade_fragments()(implicit c: Character) {
    c.travel(Areas.ForgottenQuarter)
    c.beginStorylet("A tour of the quarter")
    c.chooseBranch("The exploitation of knowledge")
    c.onwards()
  } 
  
  //12+ actions for 1 mystery (12.5E)
  def antique_mysteries()(implicit c: Character) {
    gear.shadowy()
    if (c.qualities("Casing...") < 10)
      heist.casing_optimal()
    else
      heist.steal_antique_mystery()
  }
  
  /************/
  /* INFERNAL */
  /************/
  //+28 soul (0.56E)
  def souls()(implicit c: Character) {
    c.travel(Areas.LadybonesRoad)
    c.beginStorylet("Track down a Spirifer")
    c.chooseBranch("Watch from the rooftops")
    c.onwards()
  }
  
  //12+ actions for 25 brilliants (12.5E)
  def brilliant_souls()(implicit c: Character) {
    gear.shadowy()
    if (c.qualities("Casing...") < 10)
      heist.casing_optimal()
    else
      heist.steal_brilliant_souls()
  }
  
  //12+ actions for 5 brandy (12.5E)
  def muscaria_brandy()(implicit c: Character) {
    gear.shadowy()
    if (c.qualities("Casing...") < 10)
      heist.casing_optimal()
    else
      heist.steal_muscaria_brandy()
  }
  
  /*************/
  /* MYSTERIES */
  /*************/
  //12+ actions for 25 tales (12.5E)
  def tales_of_terror()(implicit c: Character) {
    gear.shadowy()
    if (c.qualities("Casing...") < 10)
      heist.casing_optimal()
    else
      heist.steal_tales_of_terror()
  }
  
  //12+ actions for 25 journals (12.5E)
  def journals_of_infamy()(implicit c: Character) {
    gear.shadowy()
    if (c.qualities("Casing...") < 10)
      heist.casing_optimal()
    else
      heist.steal_journals_of_infamy()
  }
  
  /*************/
  /* MYSTERIES */
  /*************/
  //12+ actions for 1 (12.5E)
  def bazaar_permits()(implicit c: Character) {
    gear.shadowy()
    if (c.qualities("Casing...") < 10)
      heist.casing_optimal()
    else
      heist.steal_tales_of_terror()
  }
}