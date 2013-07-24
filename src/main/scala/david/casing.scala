package david
import london._

//notes on difficulty:
//diff X = 60% chance at qual X, 100% at qual X*5/3.
//it scales linearly with a factor of the base diff
//some of the prep actions have rare successes which give you more cp

object casing {
  //3 actions, 6cp (8 rare), difficulty 75
  def prep1()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Preparing for a Big Score")
    c.chooseBranch("Look for the targets")
    c.onwards()
  }
  
  //3 actions, 6cp, difficulty 76
  //fail: 1-2cp suspicion
  def prep2()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Preparing for a Big Score")
    c.chooseBranch("Examine the target")
    c.onwards()
  }
  
  //3 actions, 6cp & +20 secrets, difficulty 78
  //fail: +suspicion, -casing
  def prep3()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Preparing for a Big Score")
    c.chooseBranch("Criminal assistance")
    c.onwards()
  }
  
  //3 actions, 9cp, difficulty 80
  //fail: +suspicion
  def prep4()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Preparing for a Big Score")
    c.chooseBranch("Scapegoats and alibis")
    c.onwards()
  }
  
  //3 actions, 9cp (rare +vigor), difficulty 82
  //fail: +suspicion
  def prep5()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Preparing for a Big Score")
    c.chooseBranch("Formulate a plan")
    c.onwards()
  }
  
  //3 actions, 9cp & bundle 30, difficulty 84
  //fail: +suspicion
  def prep6()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Preparing for a Big Score")
    c.chooseBranch("The decoy")
    c.onwards()
    c.chooseBranch()
    c.onwards()
  }

  //casing 3, 100%
  //+10cp criminals, +10cp shadowy
  def sell_info_constables()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Sell information")
    c.chooseBranch("Pass information to the Constables")
    c.onwards()
  }
  
  //casing 3, 100%
  //+30cp hell
  def sell_info_hell()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Sell information")
    c.chooseBranch("Pass information to the Brass Embassy")
    c.onwards()
  }
  
  //casing 4, 100%
  //+10cp criminals, +10cp shadowy
  def sell_info_criminals()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Sell information")
    c.chooseBranch("Pass information to a colleague")
    c.onwards()
  }

  //casing 5, 70%/30%
  //432E/836E
  def target_carnival()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Steal the carnival strong box")
    c.chooseBranch("Rob the strong-box halfway through the evening")
    //c.chooseBranch("Rob the strong-box at the end of a busy evening")
    c.onwards()
  }
  
  //casing 7, 70%/30%
  //XXX probably proscribed material
  def target_ministry()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Rob the Ministry of Public Decency")
    c.chooseBranch("Rob an outlying depository")
    //c.chooseBranch("Rob the main archive")
    c.onwards()
  }
  
  //casing 9, 70%/30%
  //XXX probably glim
  def target_shipment()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Rob the glim shipment")
    c.chooseBranch("Rob the glim ship")
    //c.chooseBranch("Wait until it docks and rob the warehouse")
    c.onwards()
  }
  
  //casing 9, 70%/30%
  //XXX 
  def target_revolution()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Bringing revolution!")
    c.chooseBranch("Destroy a statue")
    //c.chooseBranch("Bomb a meeting of financiers")
    c.onwards()
  }
  
  //casing 11, 70%/30%
  //XXX  -20cc/
  def target_duchess()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Rob the chambers of the Duchess")
    c.chooseBranch("Rob the study")
    //c.chooseBranch("Gleams in the darkness")
    c.onwards()
  }
  
  def target_brass_embassy()(implicit c: Character) {
    
  }
}
