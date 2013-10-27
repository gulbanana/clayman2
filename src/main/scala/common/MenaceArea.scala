package common
import api._

trait MenaceArea extends OneManJob {
  def opportunities: Opportunist
  def exitStorylets: Set[String]
  def reduce_menace()(implicit c: Character): Unit
  
  def apply(implicit c: Character): Boolean = {
    if (!opportunities.played()) reduce_menace()
    if (exitStorylets(c.currentStorylet)) c.onwards()
    true
  }
}