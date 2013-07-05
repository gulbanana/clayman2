package common
import london._

object grind {
  //+28 soul (0.56E)
  def ladybones_souls()(implicit c: Character) {
    c.travel(Areas.LadybonesRoad)
    c.beginStorylet("Track down a Spirifer")
    c.chooseBranch("Watch from the rooftops")
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
  
  //+37 greyfields 1888 (0.74E)
  //failure: 2cp scandal
  //max persuasive 85
  def palace_wines()(implicit c: Character) {
    c.travel(Areas.ShutteredPalace)
    c.beginStorylet("The Antiquarian Footman")
    c.chooseBranch("Offer the fellow a partnership")
    c.onwards()
  }  
  
  //XXX there are better wolfstack storylets, these are just the kindofsafe ones for dangerous 69
  
  //+65 glim (0.65E)
  def wolfstack_glim()(implicit c: Character) {
    c.travel(Areas.WolfstackDocks)
    c.beginStorylet("Intercept a shipment of Clay Men")
    c.chooseBranch("Scuttle the boat")
    c.onwards()
  }
  
  //+65 glim (0.65E)
  def wolfstack_silk()(implicit c: Character) {
    c.travel(Areas.WolfstackDocks)
    c.beginStorylet("Eyes and spiders")
    c.chooseBranch("Wait for a scream and pursue")
    c.onwards()
  }
}