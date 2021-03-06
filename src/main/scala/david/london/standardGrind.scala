package david.london
import api._
import common._
import david._

/* Notability
MW requirement for 60%: 20 - B+D+R + 6N
B+D+R = 23
N   MW60  MW100
0   1     1
1   3     5
2   9     15
3   15    25
4   21    35
5   27    45
6   33    55
7   39    65
 */

object standardGrind extends OneManJob {
  def apply(implicit c: Character) = {
    avertMenaces.apply || playCards.apply || stockpile || connections || achieveGoals or money
  }

  //transient goals, specific acquisitions
  private def achieveGoals(implicit c: Character) = did(c.items("Uncanny Incunabulum") < 25) {
    convert.implications_to_incunabula()
  }

  //I want to keep certain connections up
  private def connections(implicit c: Character) = did(c.qualities("Connected: Society") < 77) {
    connected.society()
  } or (c.qualities("Connected: Bohemian") < 50) {
    connected.bohemian()
  } or (c.qualities("Connected: Benthic") < 10) {
    connected.university()
  }

  //For efficient convertibility, multiples of 10/50/25/62.5 echoes are required
  private def stockpile(implicit c: Character) = farmGoods(20) || farmT1(20) || farmT2(50) || farmT3(50) || farmT4(62.5) || farmT5(62.5)

  private def farmGoods(echoes: Double)(implicit c: Character) = did(c.items("Rostygold") < (100 * echoes)) {
    grind.rostygold()
  } or (c.items("Moon-Pearl") < (100 * echoes)) {
    grind.moon_pearls()
  } or (c.items("Nevercold Brass Sliver") < (100 * echoes)) {
    grind.nevercold_brass_slivers()
  } or (c.items("Deep Amber") < (100 * echoes)) {
    grind.deep_amber()
  } or (c.items("Rat on a String") < (100 * echoes)) {
    grind.rats_on_strings()
  } or (c.items("An Infernal Contract") < (5 * echoes)) {
    grind.infernal_contracts()
  } or (c.items("Surface Currency") < (100 * echoes / 3)) {
    grind.surface_currency()
  }

  private def farmT1(echoes: Double)(implicit c: Character) = did(c.items("Cryptic Clue") < (50 * echoes)) {
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

  private def farmT2(echoes: Double)(implicit c: Character) = did(c.items("Appalling Secret") < ((1.0 / 0.15) * echoes)) {
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

  private def farmT3(echoes: Double)(implicit c: Character) = did(c.items("A Journal of Infamy") < (2 * echoes)) {
    grind.journals_of_infamy()
  } or (c.items("Tale of Terror!!") < (2 * echoes)) {
    grind.tales_of_terror()
  } or (c.items("Brilliant Soul") < (2 * echoes)) {
    grind.brilliant_souls()
  } or (c.items("Compromising Document") < (2 * echoes)) {
    convert.gossip_to_documents()
  } or (c.items("Memory of Distant Shores") < (2 * echoes)) {
    convert.salts_to_memories()
  } or (c.items("Memory of Light") < (2 * echoes)) {
    convert.scarabs_to_memories()
  } or (c.items("Incendiary Gossip") < (2 * echoes)) {
    convert.inklings_to_gossip()
  } or (c.items("Zee-Ztory") < (2 * echoes)) {
    convert.scraps_to_ztories()
  } or (c.items("Mystery of the Elder Continent") < (2 * echoes)) {
    convert.relics_to_mysteries()
  } or (c.items("Strangling Willow Absinthe") < (2 * echoes)) {
    grind.strangling_willow_absinthe()
  } or (c.items("Vision of the Surface") < (2 * echoes)) {
    convert.notions_to_visions()
  } or (c.items("Whisper-Satin Scrap") < (2 * echoes)) {
    convert.surface_silk_to_whisper_satin()
  } or (c.items("Correspondence Plaque") < (2 * echoes)) {
    convert.prayers_to_plaques()
  }

  private def farmT4(echoes: Double)(implicit c: Character) = did(c.items("Extraordinary Implication") < echoes / 2.5) {
    convert.journals_to_implications()
  } or (c.items("Muscaria Brandy") < echoes / 2.5) {
    convert.brilliant_to_brandy()
  } or (c.items("Stolen Kiss") < echoes / 2.5) {
    convert.documents_to_kisses()
  } or (c.items("Collated Research") < echoes / 2.5) {
    convert.memories_to_research()
  } or (c.items("Mourning Candle") < echoes / 2.5) {
    convert.memories_to_candles()
  } or (c.items("An Identity Uncovered!") < echoes / 2.5) {
    convert.gossip_to_identities()
  } or (c.items("Partial Map") < echoes / 2.5) {
    convert.ztories_to_maps()
  } or (c.items("Presbyterate Passphrase") < echoes / 2.5) {
    convert.mysteries_to_passphrases()
  } or (c.items("Broken Giant 1844") < echoes / 2.5) {
    convert.strangling_willow_to_broken_giant()
  } or (c.items("Touching Love Story") < echoes / 2.5) {
    convert.visions_to_stories()
  } or (c.items("Thirsty Bombazine Scrap") < echoes / 2.5) {
    convert.whisper_satin_to_thirsty_bombazine()
  } or (c.items("Aeolian Scream") < echoes / 2.5) {
    convert.plaques_to_screams()
  }

  private def farmT5(echoes: Double)(implicit c: Character) = did(c.items("Uncanny Incunabulum") < echoes / 12.5) {
    convert.implications_to_incunabula()
  } or (c.items("Brass Ring") < echoes / 12.5) {
    convert.brandy_to_brass()
  } or (c.items("Favours in High Places") < echoes / 12.5) {
    convert.kisses_to_favours()
    //Academic - Collated Research -> ???
    //Luminosity - Mourning Candle -> Patent Scrutinizer
    //Rumour - An Identity Uncovered! -> Blackmail Material
    //Cartography - Partial Map -> Puzzling Map
    //Elder - Presbyterate Passphrase -> Antique Mystery
  } or (c.items("Cellar of Wine") < echoes / 12.5) {
    convert.broken_giant_to_cellars()
  }
  //Nostalgia - Touching Love Story -> Bazaar Permit
  //Thirsty Bombazine Scrap -> Puzzle-Damask
  //Wild Words - Aeolian Scream -> Storm-Threnody

  private def money(implicit c: Character) {
    fidgetingWriter.apply(c)
  }
}