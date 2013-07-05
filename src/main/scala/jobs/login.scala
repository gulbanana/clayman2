package jobs
import london._
import common._

//just a test job
object login extends OneManJob {
  def apply(implicit c: Character) {
    //discard: Undertakings
    //discard: The notable citizen //max reward is 83 whispered secrets, not good enough
    c.playOpportunity("A mission from the Cheesemonger")
    c.chooseBranch("Take the shipment")
    c.onwards()
    c.drawOpportunities()
  }
}