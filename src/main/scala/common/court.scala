package common
import london._

object court {
  //+83 whispered secrets
  //failure: -fascinating
  def secrets()(implicit c: Character) {
    c.beginStory("Attend to matters of romance")
    c.chooseBranch("Attend courtly functions")
    c.onwards()
  }
}