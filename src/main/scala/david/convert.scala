package david
import api._

object convert { 
  /*************/
  /* ACADEMIC */
  /*************/
  def candles_to_salts()(implicit c: Character) {
    c.useItem("Foxfire Candle Stub")
    c.chooseBranch("Make a substantial donation to F.F. Gebrandt's church")
    c.onwards()    
  }
  
  def salts_to_memories()(implicit c: Character) {
    c.useItem("Abominable Salts")
    c.chooseBranch("Sell your poison to zailors bound for the Elder Continent")
    c.onwards()    
  }
  
  def memories_to_research()(implicit c: Character) {
    c.useItem("Memory of Distant Shores")
    c.chooseBranch("Dealing with the deviless")
    c.onwards()    
  }
  
  /***************/
  /* CARTOGRAPHY */
  /***************/
  def glim_to_scraps()(implicit c: Character) {
    c.useItem("Glim")
    c.chooseBranch("Trade a sack of glim")
    c.onwards()
  } 
  
  def scraps_to_ztories()(implicit c: Character) {
    c.useItem("Map Scrap")
    c.chooseBranch("Show your map scraps to a gathering of zee-captains")
    c.onwards()
  } 
  
  def ztories_to_maps()(implicit c: Character) {
    c.useItem("Zee-Ztory")
    c.chooseBranch("Tell tall tales with the Salty Fabulist")
    c.onwards()
  } 
  
  /*********/
  /* ELDER */
  /*********/
  def jade_to_relics()(implicit c: Character) {
    c.useItem("Jade Fragment")
    c.chooseBranch("Buy out a shop of antiquities with your jade ")
    c.onwards()
  } 
  
  def relics_to_mysteries()(implicit c: Character) {
    c.useItem("Relic of the Third City")
    c.chooseBranch("Sell your relics to Feducci")
    c.onwards()
  } 
  
  def mysteries_to_passphrases()(implicit c: Character) {
    c.useItem("Mystery of the Elder Continent")
    c.chooseBranch("Distressing bird")
    c.onwards()
  } 
  
  /************/
  /* INFERNAL */
  /************/
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
  
  /*************/
  /* INFLUENCE */
  /*************/
  def correspondence_to_gossip()(implicit c: Character) {
    c.useItem("Stolen Correspondence")
    c.chooseBranch("Spend a week decoding encrypted letters")
    c.onwards()    
  }

  def gossip_to_documents()(implicit c: Character) {
    c.useItem("Intriguing Gossip")
    c.chooseBranch("Just write the things down")
    c.onwards()    
  }
  
  def documents_to_kisses()(implicit c: Character) {
    c.useItem("Compromising Document")
    c.chooseBranch("A romantic sort of compromise")
    c.onwards()    
  }
  
  def kisses_to_favours()(implicit c: Character) {
    c.useItem("Stolen Kiss")
    c.chooseBranch("Ask the Muffled Intriguer to broker your Stolen Kisses")
    c.onwards()    
  }
  
  /**************/
  /* LUMINOSITY */
  /**************/
  def beeswax_to_scarabs()(implicit c: Character) {
    c.useItem("Lamplighter Beeswax")
    c.chooseBranch("Offload a great quantity of wax")
    c.onwards()    
  }
  
  def scarabs_to_memories()(implicit c: Character) {
    c.useItem("Phosphorescent Scarab")
    c.chooseBranch("The Radical Factotum will be pleased to take them from you")
    c.onwards()    
  }
  
  def memories_to_candles()(implicit c: Character) {
    c.useItem("Memory of Light")
    c.chooseBranch("Trying to remember")
    c.onwards()    
  }
  
  /*************/
  /* MYSTERIES */
  /*************/
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
  
  def journals_to_implications()(implicit c: Character) {
    c.useItem("A Journal of Infamy")
    c.chooseBranch("Study your journals with a learned colleague")
    c.onwards()
  } 

  def implications_to_incunabula()(implicit c: Character) {
    c.useItem("Extraordinary Implication")
    c.chooseBranch("Benthic's Press")
    c.onwards()
  } 
  
  /*************/
  /* NOSTALGIA */
  /*************/
  def honey_to_notions()(implicit c: Character) {
    c.useItem("Drop of Prisoner's Honey")
    c.chooseBranch("Sponsor a dream-expedition for lovers")
    c.onwards()
  } 
  
  def notions_to_visions()(implicit c: Character) {
    c.useItem("Romantic Notion")
    c.chooseBranch("Hold a salon dedicated to nostalgia for the Surface")
    c.onwards()
  } 
  
  def visions_to_stories()(implicit c: Character) {
    c.useItem("Vision of the Surface")
    c.chooseBranch("Speak with bohemians about lost loves and the Surface")
    c.onwards()
  } 

  /*************/
  /* RAG TRADE */
  /*************/
  def silk_to_surface_silk()(implicit c: Character) {
    c.useItem("Silk Scrap")
    c.chooseBranch("Swap a great deal of silk")
    c.onwards()
  } 
  
  def surface_silk_to_whisper_satin()(implicit c: Character) {
    c.useItem("Surface-Silk Scrap")
    c.chooseBranch("Take an armful of bolts to the Marred Mercer")
    c.onwards()
  } 
  
  def whisper_satin_to_thirsty_bombazine()(implicit c: Character) {
    c.useItem("Whisper-Satin Scrap")
    c.chooseBranch("Trade them to the Marred Mercer")
    c.onwards()
  } 
  
  /**********/
  /* RUMOUR */
  /**********/
  def material_to_inklings()(implicit c: Character) {
    c.useItem("Proscribed Material")
    c.chooseBranch("Study an armful of forbidden books")
    c.onwards()
  } 
  
  def inklings_to_gossip()(implicit c: Character) {
    c.useItem("Inkling of Identity")
    c.chooseBranch("Agree to forget an identity you've confirmed")
    c.onwards()
  } 
  
  def gossip_to_identities()(implicit c: Character) {
    c.useItem("Incendiary Gossip")
    c.chooseBranch("Speculate on the identity of certain parties at a salon")
    c.onwards()
  }
  
  /**************/
  /* WILD WORDS */
  /**************/
  def shrieks_to_prayers()(implicit c: Character) {
    c.useItem("Primordial Shriek")
    c.chooseBranch("Let your shrieks loose on the Square of Lofty Words")
    c.onwards()
  } 
  
  def prayers_to_plaques()(implicit c: Character) {
    c.useItem("Maniac's Prayer")
    c.chooseBranch("Repeat a great many Maniac's Prayers")
    c.onwards()
  } 
  
  def plaques_to_screams()(implicit c: Character) {
    c.useItem("Correspondence Plaque")
    c.chooseBranch("Unlikely customers")
    c.onwards()
  } 
  
  /*********/
  /* WINES */
  /*********/
  def greyfields_to_morelways()(implicit c: Character) {
    c.useItem("Greyfields 1882")
    c.chooseBranch("Help out with the harvest festival")
    c.onwards()
  } 
  
  def morelways_to_strangling_willow()(implicit c: Character) {
    c.useItem("Morelways 1872")
    c.chooseBranch("Trade them off to the Portly Sommelier")
    c.onwards()
  }
  
  def strangling_willow_to_broken_giant()(implicit c: Character) {
    c.useItem("Strangling Willow Absinthe")
    c.chooseBranch("Interrupt the Affable Monsignor's reading")
    c.onwards()
  }
}