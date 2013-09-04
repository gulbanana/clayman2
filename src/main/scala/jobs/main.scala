package jobs
import api._
import common._
import david._

object main extends BufferedJob {
  def apply(implicit c: Character) = c.location match {
    case Areas.NewNewgate    => prison.opportunities.played() or prison.reduce_suspicion()
    case Areas.TombColonies  => colonies.opportunities.played() or colonies.reduce_scandal()
    case Areas.BroadUnterzee => unterzee.southernArchipelago(c)
    case Areas.SeaOfVoices   => unterzee.seaOfVoices(c)
    case Areas.GruntingFen   => unterzee.gruntingFen(c)
    case x if londonAreas(x) => london.standardGrind(c)
    case _                   => false
  } 
  
  private val londonAreas = Set(
    Areas.BazaarSideStreets,
    Areas.Carnival,
    Areas.DoubtStreet,
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