package common
import london._

object grind {
  //+28 soul (0.56E)
  def ladybones_souls()(implicit c: Character) {
    c.travel(Areas.LadybonesRoad)
    c.beginStorylet("Track down a Spirifer")
    c.chooseBranch("Watch from the rooftops")
    c.onwards()
  }
  
  //+54 rostygold with a rare success for appalling secrets
  def ladybones_rostygold()(implicit c: Character) {
    c.travel(Areas.LadybonesRoad)
    c.beginStorylet("Industrial Espionage!")
    c.chooseBranch("Investigate the Embassy warehouses")
    c.onwards()
  }
  
  //+82 jade fragments (0.75E)
  //failure: scandal
  //max persuasive 88
  def palace_jade()(implicit c: Character) {
    c.travel(Areas.ShutteredPalace)
    c.beginStorylet("Become a trader in antiques")
    c.chooseBranch("The exploitation of knowledge")
    c.onwards()
  }
  
  //+37 greyfields 1888 (0.74E)
  //failure: 2cp scandal
  //max persuasive 85
  def palace_wines()(implicit c: Character) {
    c.travel(Areas.ShutteredPalace)
    c.beginStorylet("The Antiquarian Footman")
    c.chooseBranch("Offer the fellow a partnership")
    c.onwards()
  } 
  
  //+82 jade fragments (0.82E)
  //failure: 2cp scandal
  def quarter_jade()(implicit c: Character) {
    c.travel(Areas.ForgottenQuarter)
    c.beginStorylet("A tour of the quarter")
    c.chooseBranch("The exploitation of knowledge")
    c.onwards()
  } 
  
  //+70 glim (0.70E)
  def wolfstack_glim()(implicit c: Character) {
    c.travel(Areas.WolfstackDocks)
    c.beginStorylet("A ship in the night")
    c.chooseBranch()
    c.onwards()
  }
  
  //+65 silk (0.65E)
  def wolfstack_silk()(implicit c: Character) {
    c.travel(Areas.WolfstackDocks)
    c.beginStorylet("Eyes and spiders")
    c.chooseBranch("Wait for a scream and pursue")
    c.onwards()
  }
  
  //+42 silk, 14 clues (0.70E)
  def wolfstack_silk_clues()(implicit c: Character) {
    c.travel(Areas.WolfstackDocks)
    c.beginStorylet("The spiders' crypt")
    c.chooseBranch("Laughing in the fangs of fear")
    c.onwards()
  }
  
/*

def cryptic_clues(character):
    """+30 cryptic clues (0.60E)"""
    character.travel(areas.VEILGARDEN)
    character.begin_story('Correspond with a contact at the Shuttered Palace')
    character.choose_branch('Write a letter')
    character.onwards()

def cryptic_clues_watchful(character):
    """+39 cryptic clues (0.78E)"""
    character.travel(areas.FORGOTTEN_QUARTER)
    character.begin_story('Understanding the Correspondence')
    character.choose_branch('A distinctly cautious approach')
    character.onwards()

def whispered_secrets(character):
    """+54 whispered secrets per action (0.54E)"""
    character.travel(areas.VEILGARDEN)
    character.begin_story('The demands of high society')
    character.choose_default_branch()
    character.onwards()

# Rumour

def proscribed_material(character):
    """+54 whispered secrets per action (0.54E)"""
    character.travel(areas.VEILGARDEN)
    character.begin_story('Charm an influential social butterfly')
    character.choose_default_branch()
    character.onwards()
 */
}