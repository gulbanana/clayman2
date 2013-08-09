package jobs
import london._
import common._
import david._

object standardGrind extends BufferedJob {
  def apply(implicit c: Character) = {
    avertMenaces.apply || playCards.apply || stockpile || achieveGoals or money
  }

  def achieveGoals(implicit c: Character) = did (c.items("Antique Mystery") < 50) {
    grind.antique_mysteries()
  } or (c.items("Muscaria Brandy") < 120) {
    grind.muscaria_brandy()
  } or (c.items("Brilliant Soul") < 600) {
    grind.brilliant_souls()
  }
  
  //For efficient convertibility, multiples of 10/50/25/62.5 echoes are required
  def stockpile(implicit c: Character) = farmGoods(10) || farmT1(20) || farmT2(10)
  
  def farmGoods(echoes: Int)(implicit c: Character) = did (c.items("Rostygold") < (100 * echoes)) {
    grind.rostygold()
  } or (c.items("Moon-pearl") < (100 * echoes)) {
    grind.moon_pearls()
  } or (c.items("Nevercold Brass Sliver") < (100 * echoes)) {
    grind.nevercold_brass_slivers()
  } or (c.items("Deep Amber") < (100 * echoes)) {
    grind.deep_amber()
  } or (c.items("Rat on a String") < (100 * echoes)) {
    grind.rats_on_strings()
  } or (c.items("An Infernal Contract") < (5 * echoes)) {
    grind.infernal_contracts()
  }
  
  def farmT1(echoes: Int)(implicit c: Character) = did (c.items("Cryptic Clue") < (50 * echoes)) {
    grind.cryptic_clues()
  } or (c.items("Soul") < (50 * echoes)) {
    grind.souls()
  } or (c.items("Stolen Correspondence") < (20 * echoes)) {
    grind.stolen_correspondence()
  } or (c.items("Foxfire Candle Stub") < (100 * echoes)) {
    grind.foxfire_candle_stubs()
  } or (c.items("Lamplighter Beeswax") < (100 * echoes)) {
    grind.lamplighter_beeswax()
  } or (c.items("Proscribed Material") < (25 * echoes)) {
    grind.proscribed_material()
  } or (c.items("Glim") < (100 * echoes)) {
    grind.glim()
  } or (c.items("Jade Fragment") < (100 * echoes)) {
    grind.jade_fragments()
  } or (c.items("Greyfields 1882") < (50 * echoes)) {
    grind.greyfields_1882()
  } or (c.items("Drop of Prisoner's Honey") < (50 * echoes)) {
    grind.prisoners_honey()
  } or (c.items("Silk Scrap") < (100 * echoes)) {
    grind.silk_scraps()
  } or (c.items("Primordial Shriek") < (50 * echoes)) {
    grind.primordial_shrieks()
  }
  
  def farmT2(echoes: Int)(implicit c: Character) = did (c.items("Appalling Secret") < ((8.0/3.0) * echoes)) {
    convert.cryptic_to_appalling()
  } or (c.items("Amanita Sherry") < (10 * echoes)) {
    convert.souls_to_sherry()
  } or (c.items("Intriguing Gossip") < (5 * echoes)) {
    convert.correspondence_to_gossip()
  } or (c.items("Abominable Salts") < (10 * echoes)) {
    convert.candles_to_salts()
  } or (c.items("Phosphorescent Scarab") < (10 * echoes)) {
    convert.beeswax_to_scarabs()
  } or (c.items("Inkling of Identity") < (10 * echoes)) {
    convert.material_to_inklings()
  } or (c.items("Map Scrap") < (10 * echoes)) {
    convert.glim_to_scraps()
  } or (c.items("Relic of the Third City") < (10 * echoes)) {
    grind.relics_of_the_third_city()
  } or (c.items("Morelways 1872") < (10 * echoes)) {
    convert.greyfields_to_morelways()
  } or (c.items("Romantic Notion") < (10 * echoes)) {
    convert.honey_to_notions()
  } or (c.items("Surface-Silk Scrap") < (10 * echoes)) {
    convert.silk_to_surface_silk()
  } or (c.items("Maniac's Prayer") < (10 * echoes)) {
    convert.shrieks_to_prayers()
  }
  
  def money(implicit c: Character) {
    grind.tales_of_terror()	//for later use with the Fidgeting Writer
  }
}