package jobs
import london._
import common._

object loiter extends OneManJob {
  def apply(implicit c: Character) { 
    common.court.secrets()
  }
}