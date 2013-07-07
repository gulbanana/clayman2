package jobs
import london._
import common._

object getDangerous extends OneManJob {
  def apply(implicit c: Character) {
    opportunities.mill()
    gear.dangerous()
    grind.wolfstack_glim()
  }
}