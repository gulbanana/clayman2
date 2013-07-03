package jobs
import common.{grind => g}

object grind extends Job {
  def apply() { 
    common.with_character { implicit c =>
      common.with_buffer { 
        c.actions -= 1
        
        g.jade()
      }
    }
  }
}