package hors
import api._
import common._

object main extends BufferedJob {
  def apply(implicit c: Character) = c.location match {
    case x if Areas.London(x) => opportunities.played() || raiseStats(c)
    case _                    => false
  } 
}