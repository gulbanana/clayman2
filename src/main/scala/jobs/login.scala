package jobs

//this job does nothing but log in, as a test
object login extends Job {
  def apply() {
    common.with_character { implicit c =>
      println("Done.")
    }
  }
}