package jobs
import london._
import common._
import david._

object flitGrind extends BufferedJob {
  def apply(implicit c: Character) = {
    gear.shadowy()
    avertMenaces.apply || playCards.apply || prep || stealMysteries || stealSouls or stealTales
  }
  
  def prep(implicit c: Character) = did (c.qualities("Casing...") < 10) {
    flit.casing_optimal()
  }
  
  //for the Royal Beth
  def stealMysteries(implicit c: Character) = did(c.items("Antique Mystery") < 50) {
    flit.steal_antique_mystery()
  }
  
  //for the Embassy
  def stealSouls(implicit c: Character) = did(c.items("Muscaria Brandy") < 120) {
    flit.steal_muscaria_brandy()
  } or (c.items("Brilliant Soul") < 600) {
    flit.steal_brilliant_souls()
  }
  
  //for the Fidgeting Writer
  def stealTales(implicit c: Character) {
    flit.steal_tales_of_terror()
  }
}