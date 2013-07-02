package jobs

object grind extends Job {
  def apply(implicit c: london.Character) { 
    common.with_buffer { c => 
      c.actions -= 1
    }
  }
}