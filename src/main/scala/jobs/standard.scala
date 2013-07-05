package jobs
import london._
import common._

object standard extends OneManJob {
  def apply(implicit c: Character) = repeat {
    grind.quarter_jade()
  }
}