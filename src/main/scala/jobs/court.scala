package jobs
import london._
import common.{grind => g}

object court extends OneManJob {
  def apply(implicit c: Character) { 
    common.court.secrets()
  }
}