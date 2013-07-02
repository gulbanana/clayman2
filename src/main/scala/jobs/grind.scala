package jobs

object grind extends Job {
  def apply(implicit c: london.Character) { 
    common.with_buffer { implicit c => 
      c.actions -= 1
      
      common.grind.jade()
    }
  }
}