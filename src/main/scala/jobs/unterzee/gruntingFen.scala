package jobs.unterzee
import api._
import common._
import david.unterzee._

object gruntingFen extends RepeatedJob {
  def apply(implicit c: Character) = did (c.qualities("Orthos is Coming!") < 4) {
    islands.fen_T1()
  } or (c.qualities("Orthos is Coming!") < 6) {
    islands.fen_T4()
  } or (c.qualities("Orthos is Coming!") == 6) {
    islands.fen_T6()
  } or (c.qualities("Orthos is Coming!") == 7) {
    islands.fen_T7()
  } or (c.qualities("Orthos is Coming!") == 8) {
    islands.fen_T8()
  } or (c.qualities("Orthos is Coming!") == 9) {
    islands.fen_T9()
  } or (c.qualities("Orthos is coming!") == 10) {
    islands.fen_T10()
  }
}