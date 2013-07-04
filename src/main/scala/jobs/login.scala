package jobs
import london.Character

//this job does nothing but log in, as a test
object login extends OneManJob {
  def apply(implicit c: Character) {
    println("Done.")
  }
}