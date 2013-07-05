package jobs
import london._
import common._

//hang around the court grinding
object loiter extends OneManJob {
  def apply(implicit c: Character) { 
    court.secrets()
  }
}