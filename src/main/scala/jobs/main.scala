package jobs
import api._
import common._
import david._

object main extends BufferedJob {
  def apply(implicit c: Character) = did (c.location == Areas.NewNewgate) {
    prison.opportunities.played() or prison.reduce_suspicion()
  } or (c.location == Areas.TombColonies) {
    colonies.opportunities.played() or colonies.reduce_scandal()
  } or (londonAreas.contains(c.location)) {
    london.standardGrind(c)
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
}