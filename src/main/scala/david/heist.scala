package david
import london._

//notes on difficulty:
//diff X = 60% chance at qual X, 100% at qual X*5/3.
//it scales linearly with a factor of the base diff
//some of the prep actions have rare successes which give you more cp

object heist {
  //3 actions, 6cp (8 rare), difficulty 75
  def casing1()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Preparing for a Big Score")
    c.chooseBranch("Look for the targets")
    c.onwards()
  }
  
  //3 actions, 6cp, difficulty 76
  //fail: 1-2cp suspicion
  def casing2()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Preparing for a Big Score")
    c.chooseBranch("Examine the target")
    c.onwards()
  }
  
  //3 actions, 6cp & +20 secrets, difficulty 78
  //fail: +suspicion, -casing
  def casing3()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Preparing for a Big Score")
    c.chooseBranch("Criminal assistance")
    c.onwards()
  }
  
  //3 actions, 9cp, difficulty 80
  //fail: +suspicion
  def casing4()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Preparing for a Big Score")
    c.chooseBranch("Scapegoats and alibis")
    c.onwards()
  }
  
  //3 actions, 9cp (rare +vigor), difficulty 82
  //fail: +suspicion
  def casing5()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Preparing for a Big Score")
    c.chooseBranch("Formulate a plan")
    c.onwards()
  }
  
  //3 actions, 9cp & bundle 30, difficulty 84
  //fail: +suspicion
  def casing6()(implicit c: Character) {
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
  //59 material & 200 clues & 40 correspondence / ???
  def target_ministry()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Rob the Ministry of Public Decency")
    c.chooseBranch("Rob an outlying depository")
    //c.chooseBranch("Rob the main archive")
    c.onwards()
  }
  
  //casing 9, 70%/30%
  //1296 glim / XXX 
  def target_shipment()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Rob the glim shipment")
    c.chooseBranch("Rob the glim ship")
    //c.chooseBranch("Wait until it docks and rob the warehouse")
    c.onwards()
  }
  
  //casing 9, 70%/30%
  //200 proscribed & 90 stolen & 50 cp / 300 proscribed & 200 cryptics & 100 cp
  def target_revolution()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Bringing revolution!")
    c.chooseBranch("Destroy a statue")
    //c.chooseBranch("Bomb a meeting of financiers")
    c.onwards()
  }
  
  //casing 11, 70%/30%wring flesh
  //300 stolen correspondence & 50 cryptic clues & 242 whispered secrets & 2 appalling secrets / 1000 pearl & 1000 jade & 5 rat 
  def target_duchess()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Rob the chambers of the Duchess")
    c.chooseBranch("Rob the study")
    //c.chooseBranch("Gleams in the darkness")
    c.onwards()
  }
  
  //casing 13 50%, 18 100%
  //1000 nevercold brass, 500 souls, 40 infernal contracts, 10 flawed diamonds, 25 proscribed material
  //fail: arrested
  def target_brass_embassy()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Rob the Brass Embassy")
    c.chooseBranch()
    c.onwards()    
  }
  
  //thefts: req casing 10, shadowy challenges - 38% at 77 - only eats <27cp casing (but double/more if you fail)
  
  //+25 tales (12.5E)
  def steal_tales_of_terror()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Thefts of Particular Character")
    c.chooseBranch("Steal Tales of Terror from a noted author")
    c.onwards()    
  }
  
  //XXX
  def steal_muscaria_brandy()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Thefts of Particular Character")
    c.chooseBranch("Steal Muscaria Brandy from the Infernal Sommelier")
    c.onwards()    
  }

  //XXX probably 25 brilliants
  def steal_brilliant_souls()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Thefts of Particular Character")
    c.chooseBranch("Steal Brilliant Souls en route to Hookman House")
    c.onwards()    
  }
  
  //+1 antique (12.5E)
  def steal_antique_mystery()(implicit c: Character) {
    c.travel(Areas.TheFlit)
    c.beginStorylet("Thefts of Particular Character")
    c.chooseBranch("Steal an Antique Mystery from Feducci")
    c.onwards()    
  }
}
