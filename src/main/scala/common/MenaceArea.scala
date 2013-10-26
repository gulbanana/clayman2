package common
import api._

trait MenaceArea extends Duty {
  def opportunities: Opportunist
  def exitStorylets: Set[String]
  def reduce_menace()(implicit c: Character): Unit
  
  def apply(implicit c: Character): Boolean = if (exitStorylets(c.currentStorylet)) {
    c.onwards(); false
  } else {
    opportunities.played() or reduce_menace()
  }
}