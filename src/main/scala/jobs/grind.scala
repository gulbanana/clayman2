package jobs
import london._
import common.{grind => g}

object grind extends OneManJob {
  def apply(implicit c: Character) = common.repeat { implicit c: Character =>  
      //c.actions -= 1
      
      g.quarter_jade()
    }
  
}