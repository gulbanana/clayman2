package common
import london._

object grind {
  //+82 jade fragments (0.82E)
  //failure: 2cp scandal
  def quarter_jade()(implicit c: Character) {
    c.travel(Areas.ForgottenQuarter)
    c.beginStory("A tour of the quarter")
    c.chooseBranch("The exploitation of knowledge")
    c.onwards()
  }
  
  //+37 greyfields 1888 (0.74E)
  //failure: 2cp scandal
  //max persuasive 85
  def palace_wines()(implicit c: Character) {
    c.travel(Areas.ShutteredPalace)
    c.beginStory("The Antiquarian Footman")
    c.chooseBranch("Offer the fellow a partnership")
    c.onwards()
  }  
}