package common

trait Job {
  def apply()
}

trait Duty {
  def apply(implicit c: london.Character): Boolean
}

trait OneManJob extends Job with Duty {
  def apply() = with_character(c => repeat(c, apply(_)))
}
