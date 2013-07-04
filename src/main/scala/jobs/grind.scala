package jobs
import london._
import common.{grind => g}

object grind extends OneManJob {
  def apply(implicit c: Character) { 
    common.with_buffer { 
      c.actions -= 1
      
      g.jade()
    }
  }
}