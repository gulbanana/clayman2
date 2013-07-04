package jobs
import london._

object court extends OneManJob {
  def apply(implicit c: Character) { 
    common.court.secrets()
  }
}