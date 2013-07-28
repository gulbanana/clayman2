package jobs
import london._
import common._
import david._

object flitThefts extends OneManJob {
  def apply(implicit c: Character) = did (c.qualities("Suspicion") < 7 && c.qualities("Casing...") < 20) {
    heist.casing4()
  }
}