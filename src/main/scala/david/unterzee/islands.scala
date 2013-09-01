package david.unterzee
import api._

object islands {
  def fen_T1()(implicit c: Character) {
    c.beginStorylet("Grunting Fen")
    c.chooseBranch("Listen to the island")
    c.onwards()
  }
  
  def fen_T4()(implicit c: Character) {
    c.beginStorylet("What is Grunting Fen Made of?")
    c.chooseBranch("What manner of things live here?")
    c.onwards()
  }
  
  def fen_T6()(implicit c: Character) {
    c.beginStorylet("The Skull of a Long-Dead God?")
    c.chooseBranch("A place filled with spirits?")
    c.onwards()
  }
  
  def fen_T7()(implicit c: Character) {
    c.beginStorylet("The Treasures of Grunting Fen")
    c.chooseBranch("Catch some")
    c.onwards()
  }
  
  def fen_T8()(implicit c: Character) {
    c.beginStorylet("Knowledge that Seeped into the Water")
    c.chooseBranch("No more peculiar than anything else here")
    c.onwards()
  }
  
  def fen_T9()(implicit c: Character) {
    c.beginStorylet("Describe Grunting Fen in Theosophistical terms")
    c.chooseBranch("Rich insights")
    c.onwards()
  }
  
  def fen_T10()(implicit c: Character) {
    c.beginStorylet("Orthos Seeks To Take Your Work")
    c.chooseBranch("Tarry a little")
    c.onwards()
    c.onwards()
  }
}