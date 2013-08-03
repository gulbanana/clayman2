package common
import london._

trait Job {
  def apply()
}

trait Duty {
  def apply(implicit c: Character): Boolean
}

trait OneManJob extends Job with Duty {
  def apply() = with_character { c => apply(c) }
}

trait BufferedJob extends Job with Duty {
  def apply() = with_character { c =>
    val buffer = c.actionCap - 4
    while (c.actions > buffer)
      apply(c)
  }
}

trait RepeatedJob extends Job with Duty {
  def apply() = with_character(c => while (c.actions > 0 && apply(c)) ())
}