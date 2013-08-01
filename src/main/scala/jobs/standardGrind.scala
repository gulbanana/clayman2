package jobs
import london._
import common._
import david._

//For efficient convertibility, 10/50/250/etc echoes are required at each tier 
object standardGrind extends OneManJob {
  def apply(implicit c: Character) = {
    playCards(c) || reduceMenaces || stockpileT1(10) || achieveGoals || stockpileT2(50) or money
  }

  def reduceMenaces = false
  
  def achieveGoals(implicit c: Character) = flitGrind(c) 
  
  def stockpileT1(echoes: Int)(implicit c: Character) = did (c.items("Whispered Secret") < (100 * echoes)) {
    ???
  } or (c.items("Soul") < (50 * echoes)) {
    ???
  } or (c.items("Stolen Correspondence") < (20 * echoes)) {
    ???
  } or (c.items("Foxfire Candle Stub") < (100 * echoes)) {
    ???
  } or (c.items("Lamplighter Beeswax") < (100 * echoes)) {
    ???
  } or (c.items("Proscribed Material") < (25 * echoes)) {
    ???
  } or (c.items("Glim") < (100 * echoes)) {
    ???
  } or (c.items("Jade Fragment") < (100 * echoes)) {
    ???
  } or (c.items("Greyfields 1882") < (50 * echoes)) {
    ???
  } or (c.items("Drop of Prisoner's Honey") < (50 * echoes)) {
    ???
  } or (c.items("Silk Scrap") < (100 * echoes)) {
    ???
  } or (c.items("Primordial Shriek") < (50 * echoes)) {
    ???
  }
  
  def stockpileT2(echoes: Int)(implicit c: Character) = did (c.items("Appalling Secret") < ((8.0/3.0) * echoes)) {
    if (c.items("Cryptic Clue") >= 500) {
      convert.cryptic_to_appalling()
    } else {
      convert.whispered_to_cryptic()
    }
  } or (c.items("Amanita Sherry") < (10 * echoes)) {
    convert.souls_to_sherry()
  } or (c.items("Intriguing Gossip") < (5 * echoes)) {
    ???
  } or (c.items("Abominable Salts") < (10 * echoes)) {
    ???
  } or (c.items("Phosphorescent Scarab") < (10 * echoes)) {
    ???
  } or (c.items("Inkling of Identity") < (10 * echoes)) {
    ???
  } or (c.items("Map Scrap") < (10 * echoes)) {
    ???
  } or (c.items("Relic of the Third City") < (10 * echoes)) {
    ???
  } or (c.items("Morelways 1872") < (10 * echoes)) {
    ???
  } or (c.items("Romantic Notion") < (10 * echoes)) {
    ???
  } or (c.items("Surface-Silk Scrap") < (10 * echoes)) {
    ???
  } or (c.items("Maniac's Prayer") < (10 * echoes)) {
    ???
  }
  
  def money(implicit c: Character) {
    grind.quarter_jade()
  }
  
  
  
/*
        # keep 1000 of this around for the presumptuous little opportunity
        if (character.persuasive < 85
            and character.items['Greyfields 1882'] < 1000
            and character.qualities['Scandal'] < 7):
            grind.greyfields_1882(character)

        # save up for lodgings
        elif character.items['Whispered Secret'] < 80000:
            grind.whispered_secrets(character)

        elif character.items['Soul'] < 40000:
            grind.souls(character)

        # get money with risk of scandal
        elif character.qualities['Scandal'] < 7:
            grind.jade_watchful(character)

        # get money with no risk
        else:
            grind.cryptic_clues(character)
 */
}