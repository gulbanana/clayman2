package jobs
import london._
import common._
import david._

object flitGrind extends OneManJob {
  def apply(implicit c: Character) = did (c.location == Areas.NewNewgate) {
    prison.opportunities.played() or prison.reduce_suspicion()
  } or {
    gear.shadowy()
    playCards.apply || prep || stealMysteries || stealSouls or stealTales
  }
  
  def prep(implicit c: Character) = did (c.qualities("Casing...") < 20) {
    heist.casing_optimal()
  }
  
  //for the Royal Beth
  def stealMysteries(implicit c: Character) = did(c.items("Antique Mystery") < 50) {
    heist.steal_antique_mystery()
  }
  
  //for the Embassy
  def stealSouls(implicit c: Character) = did(c.items("Muscaria Brandy") < 120) {
    heist.steal_muscaria_brandy()
  } or (c.items("Brilliant Soul") < 600) {
    heist.steal_brilliant_souls()
  }
  
  //for the Fidgeting Writer
  def stealTales(implicit c: Character) {
    heist.steal_tales_of_terror()
  }
}