package jobs
import api._
import common._
import david._

object main extends BufferedJob {
  def apply(implicit c: Character) = if (c.location == Areas.NewNewgate) {
    prison.opportunities.played() or prison.reduce_suspicion()
  } else if (c.location == Areas.TombColonies) {
    colonies.opportunities.played() or colonies.reduce_scandal()
  } else if (c.location == Areas.BroadUnterzee) {
    zail(c)
  } else if (londonAreas.contains(c.location)) {
    london.standardGrind(c)
  } else if (unscriptedAreas.contains(c.location)) {
    false
  } else {
    print("No script available for area " + c.location)
    false
  }
  
  private val londonAreas = Set(
    Areas.BazaarSideStreets,
    Areas.Carnival,
    Areas.ForgottenQuarter,
    Areas.GamekeepersCottage,
    Areas.HouseOfChimes,
    Areas.LabyrinthOfTigers,
    Areas.LadybonesRoad,
    Areas.Lodgings,
    Areas.MahoganyHall,
    Areas.Spite,
    Areas.TheFlit,
    Areas.University,
    Areas.Veilgarden,
    Areas.WatchmakersHill,
    Areas.WilmotsEnd,
    Areas.WolfstackDocks
  )
  
  private val unscriptedAreas = Set(
    Areas.Court,
    Areas.DoubtStreet,
    Areas.CorpsecageIsland
  )
}