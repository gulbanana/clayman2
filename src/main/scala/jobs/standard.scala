package jobs
import london._
import common._

object standard extends OneManJob {
  def apply(implicit c: Character) = common.repeat { implicit c: Character =>  
      //c.actions -= 1
      
      grind.quarter_jade()
    }
  
}