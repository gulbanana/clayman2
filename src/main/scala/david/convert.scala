package david
import london._

object convert {
  //Mysteries
  def whispered_to_cryptic()(implicit c: Character) {
    c.useItem("Whispered Secret")
    c.chooseBranch("Combine 500 Whispered Secrets into 200 Cryptic Clues")
    c.onwards()
  } 
  
  def cryptic_to_appalling()(implicit c: Character) {
    c.useItem("Cryptic Clue")
    c.chooseBranch("Trade a great many Cryptic Clues")
    c.onwards()
  } 

  def appalling_to_infamy()(implicit c: Character) {
    c.useItem("Appalling Secret")
    c.chooseBranch("Spend a night with the cats of the Duchess")
    c.onwards()
  } 
  
  def appalling_to_terror()(implicit c: Character) {
    c.useItem("Appalling Secret")
    c.chooseBranch("Correspond with a trio of sisters")
    c.onwards()
  } 
  
  //Infernal
  def souls_to_sherry()(implicit c: Character) {
    c.useItem("Soul")
    c.chooseBranch("Offer a great many souls to the Embassy")
    c.onwards()
  } 
  
  def sherry_to_brilliant()(implicit c: Character) {
    c.useItem("Amanita Sherry")
    c.chooseBranch("Poison a gathering of spirifers")
    c.onwards()
  } 
  
  def brilliant_to_brandy()(implicit c: Character) {
    c.useItem("Brilliant Soul")
    c.chooseBranch("Exchange your souls for brandy")
    c.onwards()
  } 
  
  def brilliant_to_terror()(implicit c: Character) {
    c.useItem("Brilliant Soul")
    c.chooseBranch("Free the souls in exchange for secrets")
    c.onwards()
  } 
  
  def brandy_to_brass()(implicit c: Character) {
    c.useItem("Muscaria Brandy")
    c.chooseBranch("Trade it to the Infernal Sommelier")
    c.onwards()    
  }
  
  //Influence
  
  //Academic
  
  //Luminosity
  
  //Rumour
  
  //Cartography
  
  //Elder
  
  //Wines
  
  //Nostalgia
  
  //Rag Trade
  
  //Wild Words
}