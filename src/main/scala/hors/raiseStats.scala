package hors
import api._
import common._

object raiseStats extends RepeatedJob {
  def apply(implicit c: Character) = {
    val stats = Set(c.watchful, c.shadowy, c.dangerous, c.persuasive)

    if (c.watchful == stats.min)
      raiseWatchful
    else if (c.shadowy == stats.min)
      raiseShadowy
    else if (c.dangerous == stats.min)
      raiseDangerous
    else
      raisePersuasive
  }

  private def raiseWatchful = false
  private def raiseShadowy = false
  private def raiseDangerous = false
  private def raisePersuasive = false
}