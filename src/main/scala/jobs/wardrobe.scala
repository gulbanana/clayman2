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

object getBizarre extends Job {
  def work() = with_character(implicit c => gear.bizarre())
}

object getDreaded extends Job {
  def work() = with_character(implicit c => gear.dreaded())
}

object getRespectable extends Job {
  def work() = with_character(implicit c => gear.respectable())
}

object getBDR extends Job {
  def work() = with_character(implicit c => gear.bdr())
}

object getBalanced extends Job {
  def work() = with_character(implicit c => gear.balanced())
}