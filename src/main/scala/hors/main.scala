package hors
import api._
import common._

object main extends BufferedJob {
  def apply(implicit c: Character) = c.location match {
    case x if Areas.london(x) => opportunities.played()
    case _                    => false
  } 
}