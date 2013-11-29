package common
import api._

trait Job {
  def work()
}

trait Duty {
  def apply(implicit c: Character): Boolean
}

abstract class OneManJob(implicit c: CharacterProvider) extends Job with Duty {
  override def work() = if (c.actions > 0) apply(c)
}

abstract class UnconditionalJob(implicit c: CharacterProvider) extends Job {
  implicit lazy val _character = c.get
  override def work() {}
}

abstract class RepeatedJob(implicit c: CharacterProvider) extends Job with Duty {
  override def work() = while (c.actions > 0 && apply(c)) {}
}

abstract class BufferedJob(implicit c: CharacterProvider) extends Job with Duty {
  override def work() {
    val buffer = c.actionCap - 4 
    while (c.actions > buffer && apply(c)) {}
  }
}