package common
import london._

object grind {
  //+82 jade fragments (0.82E)
  //failure: 2cp scandal
  def jade()(implicit c: Character) {
    c.travel(Areas.ForgottenQuarter)
    //c.beginStory("A tour of the quarter")
    //c.chooseBranch("The exploitation of knowledge")
    c.onwards()
  }
}