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
  
  def bullbone_T0()(implicit c: Character) {
    c.beginStorylet("Bullbone Island")
    c.chooseBranch("The bones of Bullbone")
    c.onwards()
  }
  
  def bullbone_T4()(implicit c: Character) {
    c.beginStorylet("The Little Cave")
    c.chooseBranch("Oho - a little writing")
    c.onwards()
  }
  
  def bullbone_T6()(implicit c: Character) {
    c.beginStorylet("Where the Wild Mandrakes Grow")
    c.chooseBranch("Creaking in the breeze")
    c.onwards()
  }
  
  def bullbone_T7()(implicit c: Character) {
    c.beginStorylet("Sparkling around the Copse")
    c.chooseBranch("Acquisition and screaming") //gain glim, the other option is moon-pearls
    c.onwards()
  }
  
  def bullbone_T8()(implicit c: Character) {
    c.beginStorylet("Spiritual happenings")
    c.chooseBranch("Search the island")         //theosophistical notes (alt: aeolian screams)
    c.onwards()
  }
  
  def bullbone_T9()(implicit c: Character) {
    c.beginStorylet("Finish your notes on Bullbone Island")
    c.chooseBranch("Write up your notes")
    c.onwards()
  }
  
  def bullbone_T10()(implicit c: Character) {
    c.beginStorylet("Orthos has Found You")
    c.chooseBranch("Tarry a little")
    c.onwards()
    c.onwards()
  }
}