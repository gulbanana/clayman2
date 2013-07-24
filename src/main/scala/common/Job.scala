package common

trait Job {
  def apply()
}

trait OneManJob extends Job {
  def apply(implicit c: london.Character)
  def apply() = with_character(apply(_))
}