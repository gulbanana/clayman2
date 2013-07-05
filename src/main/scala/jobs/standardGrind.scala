package jobs
import london._
import common._

object standardGrind extends OneManJob {
  def apply(implicit c: Character) = repeat {
    grind.quarter_jade()
  }
}