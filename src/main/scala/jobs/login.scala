package jobs

//this job does nothing but log in, as a test
object login extends Job {
  def apply(implicit c: london.Character) {
    println("Logged in, %d actions".format(c.actions))
  }
}