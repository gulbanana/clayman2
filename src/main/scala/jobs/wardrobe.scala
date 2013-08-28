package jobs
import common._
import david._

object getDangerous extends Job {
  def work() = with_character(implicit c => gear.dangerous())
}

object getWatchful extends Job {
  def work() = with_character(implicit c => gear.watchful())
}

object getShadowy extends Job {
  def work() = with_character(implicit c => gear.shadowy())
}

object getPersuasive extends Job {
  def work() = with_character(implicit c => gear.persuasive())
}