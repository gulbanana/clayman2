package david
import london._

object grind {  
  /*********/
  /* GOODS */
  /*********/
  //+100 amber
  def deep_amber()(implicit c: Character) {
    gear.persuasive()
    c.travel(Areas.Veilgarden)
    c.beginStorylet("Unfinished Business in Veilgarden")
    c.chooseBranch("Get in on a card game")
    c.onwards()
  }
  
  //+5 contracts
  def infernal_contracts()(implicit c: Character) {
    gear.dangerous()
    c.travel(Areas.WatchmakersHill)
    c.beginStorylet("Unfinished Business in Watchmaker's Hill")
    c.chooseBranch("Contracts and legs")
    c.onwards()
  }
  
  //+100 pearls 
  def moon_pearls()(implicit c: Character) {
    gear.dangerous()
    c.travel(Areas.WatchmakersHill)
    c.beginStorylet("Unfinished Business in Watchmaker's Hill")
    c.chooseBranch("A spot of footpadry")
    c.onwards()
  }
  
  //+550 brass for 5 actions 
  def nevercold_brass_slivers()(implicit c: Character) {
    gear.watchful()
    c.travel(Areas.LadybonesRoad)
    c.beginStorylet("Unfinished Business in Ladybones Road")
    c.chooseBranch("Mischief and brass")
    c.onwards()
  }
  
  //+100 rats
  def rats_on_strings()(implicit c: Character) {
    gear.shadowy()
    c.travel(Areas.Spite)
    c.beginStorylet("Unfinished Business in Spite")
    c.chooseBranch("Keep your hands quick")
    c.onwards()
  }
  
  //+40 relics for 2 actions
  def relics_of_the_fourth_city()(implicit c: Character) {
    gear.watchful()
    c.travel(Areas.LadybonesRoad)
    c.beginStorylet("Unfinished Business in Ladybones Road")
    c.chooseBranch("The study of antiquities")
    c.onwards()
  }
  
  //+550 rostygold for 5 actions 
  def rostygold()(implicit c: Character) {
    gear.dangerous()
    c.travel(Areas.WatchmakersHill)
    c.beginStorylet("Unfinished Business in Watchmaker's Hill")
    if (c.items("Hard-Earned Lesson") > 0)
      c.chooseBranch("Wade into the Ring Fights", false)
    else
      c.chooseBranch("Wade into the Ring Fights")
    c.onwards()
  }
  
  //+250 currency for 6 actions (1.25E - higher difficulty..)
  def surface_currency()(implicit c: Character) {
    gear.persuasive()
    c.travel(Areas.Veilgarden)
    c.beginStorylet("Unfinished Business in Veilgarden")
    c.chooseBranch("Shake down your agent")
    c.onwards()
  }
  
  /************/
  /* ACADEMIC */
  /************/
  //+100 candles
  def foxfire_candle_stubs()(implicit c: Character) {
    gear.persuasive()
    c.travel(Areas.Veilgarden)
    c.beginStorylet("Unfinished Business in Veilgarden")
    c.chooseBranch("An admirer among the clergy")
    c.onwards()
  }
  
  /***************/
  /* CARTOGRAPHY */
  /***************/
  //+100 glim
  def glim()(implicit c: Character) {
    gear.shadowy()
    c.travel(Areas.Spite)
    c.beginStorylet("Unfinished Business in Spite")
    c.chooseBranch("Rob a glim shipment")
    c.onwards()
  }

  /*********/
  /* ELDER */
  /*********/
  //+240 jade for 2 actions (1.2E) 
  def jade_fragments()(implicit c: Character) {
    gear.dangerous()
    c.travel(Areas.WatchmakersHill)
    c.beginStorylet("Unfinished Business in Watchmaker's Hill")
    c.chooseBranch("A sure bet")
    c.onwards()
  }
  
  //+11 relics, -10 rostygold
  def relics_of_the_third_city()(implicit c: Character) {
    gear.shadowy()
    c.travel(Areas.Spite)
    c.beginStorylet("Unfinished Business in Spite")
    c.chooseBranch("The fellow in the corner")
    c.onwards()
  }
  
  //12+ actions for 1 mystery (12.5E)
  def antique_mysteries()(implicit c: Character) {
    gear.shadowy()
    if (c.qualities("Casing...") < 10)
      flit.casing_optimal()
    else
      flit.steal_antique_mystery()
  }
  
  /************/
  /* INFERNAL */
  /************/
  //+50 souls
  def souls()(implicit c: Character) {
    gear.shadowy()
    c.travel(Areas.Spite)
    c.beginStorylet("Unfinished Business in Spite")
    c.chooseBranch("The prince of...")
    c.onwards()
  }
  
  //12+ actions for 25 brilliants (12.5E)
  def brilliant_souls()(implicit c: Character) {
    gear.shadowy()
    if (c.qualities("Casing...") < 10)
      flit.casing_optimal()
    else
      flit.steal_brilliant_souls()
  }
  
  //12+ actions for 5 brandy (12.5E)
  def muscaria_brandy()(implicit c: Character) {
    gear.shadowy()
    if (c.qualities("Casing...") < 10)
      flit.casing_optimal()
    else
      flit.steal_muscaria_brandy()
  }
  
  /*************/
  /* INFLUENCE */
  /*************/
  //+40 correspondence
  def stolen_correspondence()(implicit c: Character) {
    gear.shadowy()
    c.travel(Areas.Spite)
    c.beginStorylet("Unfinished Business in Spite")
    c.chooseBranch("Ambush a few couriers for old times' sake")
    c.onwards()
  }
  
  //12+ actions for 1 (12.5E)
  def bazaar_permits()(implicit c: Character) {
    gear.shadowy()
    if (c.qualities("Casing...") < 10)
      flit.casing_optimal()
    else
      flit.steal_bazaar_permit()
  }
  
  /**************/
  /* LUMINOSITY */
  /**************/
  //+100 beeswax
  def lamplighter_beeswax()(implicit c: Character) {
    gear.watchful()
    c.travel(Areas.LadybonesRoad)
    c.beginStorylet("Unfinished Business in Ladybones Road")
    c.chooseBranch("A lovely thought")
    c.onwards()
  }
  
  /*************/
  /* MYSTERIES */
  /*************/
  //+50 clues
  def cryptic_clues()(implicit c: Character) {
    gear.shadowy()
    c.travel(Areas.Spite)
    c.beginStorylet("Unfinished Business in Spite")
    c.chooseBranch("Eavesdropping")
    c.onwards()
  }
  
  //12+ actions for 25 tales (12.5E)
  def tales_of_terror()(implicit c: Character) {
    gear.shadowy()
    if (c.qualities("Casing...") < 10)
      flit.casing_optimal()
    else
      flit.steal_tales_of_terror()
  }
  
  //12+ actions for 25 journals (12.5E)
  def journals_of_infamy()(implicit c: Character) {
    gear.shadowy()
    if (c.qualities("Casing...") < 10)
      flit.casing_optimal()
    else
      flit.steal_journals_of_infamy()
  }
  
  /*************/
  /* NOSTALGIA */
  /*************/
  //+50 honey (rare: +1 stolen kiss)
  def prisoners_honey()(implicit c: Character) {
    gear.persuasive()
    c.travel(Areas.Veilgarden)
    c.beginStorylet("Unfinished Business in Veilgarden")
    c.chooseBranch("Doing the decent thing.")
    c.onwards()
  }

  /*************/
  /* RAG TRADE */
  /*************/
  //+450 silk & ruthless for 5 actions
  def silk_scraps()(implicit c: Character) {
    gear.shadowy()
    c.travel(Areas.Spite)
    c.beginStorylet("Unfinished Business in Spite")
    c.chooseBranch("Encourage the honest folk of Spite to give you 'gifts'")
    c.onwards()
  }
  
  /**********/
  /* RUMOUR */
  /**********/
  //+25 material 
  def proscribed_material()(implicit c: Character) {
    gear.dangerous()
    c.travel(Areas.WatchmakersHill)
    c.beginStorylet("Unfinished Business in Watchmaker's Hill")
    c.chooseBranch("A literary sort of fist-fight")
    c.onwards()
  }
  
  /**************/
  /* WILD WORDS */
  /**************/
  //+50 shrieks
  def primordial_shrieks()(implicit c: Character) {
    gear.watchful()
    c.travel(Areas.LadybonesRoad)
    c.beginStorylet("Unfinished Business in Ladybones Road")
    c.chooseBranch("Debate the nature of Primordial Shrieks")
    c.onwards()
  }
  
  /*********/
  /* WINES */
  /*********/
  //+520 '79 for 5 actions
  def greyfields_1879()(implicit c: Character) {
    gear.persuasive()
    c.travel(Areas.Veilgarden)
    c.beginStorylet("Unfinished Business in Veilgarden")
    c.chooseBranch("Your crowd of admirers")
    c.onwards()
  }
  
  //+50 '82
  def greyfields_1882()(implicit c: Character) {
    gear.watchful()
    c.travel(Areas.LadybonesRoad)
    c.beginStorylet("Unfinished Business in Ladybones Road")
    c.chooseBranch("A tradition developed after the Fall")
    c.onwards()
  }
}
