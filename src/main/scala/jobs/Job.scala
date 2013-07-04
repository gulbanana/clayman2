package jobs

trait Job {
  def apply()
}

trait OneManJob extends Job {
  def apply(implicit c: london.Character)
  def apply() {
    common.with_character(apply(_))
  }
}