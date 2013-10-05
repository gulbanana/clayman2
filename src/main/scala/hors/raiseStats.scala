package hors
import api._
import common._

object raiseStats extends RepeatedJob {
  def apply(implicit c: Character) = {
    val stats = Set(c.watchful, c.shadowy, c.dangerous, c.persuasive)

    if (c.watchful == stats.min)
      raiseWatchful(c)
    else if (c.shadowy == stats.min)
      raiseShadowy(c)
    else if (c.dangerous == stats.min)
      raiseDangerous(c)
    else
      raisePersuasive(c)
  }

  private def raiseWatchful(c: Character) = {
    c.travel(Areas.LadybonesRoad)
    false
  }
  
  private def raiseShadowy(c: Character) = {
    c.travel(Areas.Spite)
    false
  }
  
  private def raiseDangerous(c: Character) = {
    c.travel(Areas.WatchmakersHill)
    false
  }
  
  private def raisePersuasive(c: Character) = {
    c.travel(Areas.Veilgarden)
    false
  }
}