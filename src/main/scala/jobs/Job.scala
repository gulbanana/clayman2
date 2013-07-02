package jobs

trait Job {
  def apply(implicit character: london.Character)
}