package hors
import api._
import common._

object playCards extends RepeatedJob {
  def apply(implicit c: Character) = opportunities.played() 
}