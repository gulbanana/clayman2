package david.unterzee
import api._
import david.gear

object keep {
  
  def T1_house()(implicit c: Character) {
    gear.persuasive()
    c.beginStorylet("Go up to the house")
    c.chooseBranch("Knock at the door")
    c.onwards()
  }
  
  //50x beeswax, 1x MODS
  def T2_well()(implicit c: Character) {
    gear.watchful()
    c.beginStorylet("Investigate the gardens")
    c.chooseBranch("Explore that mysterious hollow")
    c.onwards()
  }
  
  //2x elder mystery
  def T2_beach()(implicit c: Character) {
    gear.watchful()
    c.beginStorylet("Investigate the gardens")
    c.chooseBranch("Walk along the beach")
    c.onwards()
  }
  
  //57x cryptic clue
  def T3_cynthia()(implicit c: Character) {
    gear.persuasive()
    c.beginStorylet("Talk to Cynthia")
    c.chooseBranch("Charm her")
    c.onwards()
  }
  
  //58x cryptic clue 
  def DT3_cynthia()(implicit c: Character) {
    gear.persuasive()
    c.beginStorylet("Talk to Cynthia")
    c.chooseBranch("Swap stories with her")
    c.onwards()
  }
  
  //2x VOTS
  def T3_phoebe()(implicit c: Character) {
    gear.persuasive()
    c.beginStorylet("Talk to Phoebe")
    c.chooseBranch("Listen to her play the violin")
    c.onwards()
  }

  //50x clue, -nightmares
  def DT3_phoebe()(implicit c: Character) {
    gear.persuasive()
    c.beginStorylet("Talk to Phoebe")
    c.chooseBranch("Listen to her sing")
    c.onwards()
  }
  
  //2x incendiary gossip, 7x cryptic clue (1.14E)
  def T3_lucy()(implicit c: Character) {
    gear.watchful()
    c.beginStorylet("Talk to Lucy")
    c.chooseBranch("Talk to her")
    c.onwards()
  }

  //116x secret (1.16E)
  def DT3_lucy()(implicit c: Character) {
    gear.watchful()
    c.beginStorylet("Talk to Lucy")
    c.chooseBranch("Ask her to take a stroll with you")
    c.onwards()
  }
  
  def T3_dinner()(implicit c: Character) {
    gear.persuasive()
    c.beginStorylet("Take high tea with the ladies")
    c.chooseBranch("Tell them about yourself")
    c.onwards()
  }
  
  //1x appalling secret, 2x incendiary gossip (1.15E)
  def T3_house_attic()(implicit c: Character) {
    gear.watchful()
    c.beginStorylet("Look around the house")
    c.chooseBranch("What's in the attic?")
    c.onwards()
  }
  
  //2x compromising document (1.0E)
  def T3_house_groundfloor()(implicit c: Character) {
    gear.watchful()
    c.beginStorylet("Look around the house")
    c.chooseBranch("What's on the ground floor?")
    c.onwards()
  }

  //2x journal of infamy
  def DT3_house()(implicit c: Character) {
    gear.watchful()
    c.beginStorylet("Look around the house")
    c.chooseBranch("What's on the first floor?")
    c.onwards()
  }
  
  //2x tale of terror (1.0E)
  def T7_cynthia()(implicit c: Character) {
    gear.persuasive()
    c.beginStorylet("Find out more of Cynthia's story")
    c.chooseBranch("Try to cheer her up")
    c.onwards()
  }
  
  //2x tale of terror, 10x cryptic clue
  def DT7_cynthia()(implicit c: Character) {
    gear.persuasive()
    c.beginStorylet("Find out more of Cynthia's story")
    c.chooseBranch("Talk to her seriously")
    c.onwards()
  }
  
  //59x cryptic clue (1.18E)
  def T7_phoebe()(implicit c: Character) {
    gear.watchful()
    c.beginStorylet("Find out more of Phoebe's story")
    c.chooseBranch("Look for her in the parlour")
    c.onwards()
  }
  
  //2x MODS, 10x whispered secret
  def DT7_phoebe()(implicit c: Character) {
    gear.watchful()
    c.beginStorylet("Find out more of Phoebe's story")
    c.chooseBranch("Look for her in the garden")
    c.onwards()
  }
  
  //2x incendiary gossip (1.0E), -wounds
  def T7_lucy()(implicit c: Character) {
    gear.persuasive()
    c.beginStorylet("Find out more of Lucy's story")
    c.chooseBranch("Talk to her")
    c.onwards()
  }
  
  //1x incendiary gossip, 70x whispered secret
  def DT7_lucy()(implicit c: Character) {
    gear.persuasive()
    c.beginStorylet("Find out more of Lucy's story")
    c.chooseBranch("Bring her a little gift")
    c.onwards()
  }
  
  //60x cryptic clue (1.20E), 2x time passing
  def T7_well()(implicit c: Character) {
    gear.watchful()
    c.beginStorylet("Look more closely at the well")
    c.chooseBranch("Investigate")
    c.onwards()
  }
  
  //2x unusual love story, 2x proscribed material (1.08E)
  def T7_books()(implicit c: Character) {
    gear.watchful()
    c.beginStorylet("Examine the books")
    c.chooseBranch("Look for something significant")
    c.onwards()
  }
  
  //2x compromising document
  def DT7_books()(implicit c: Character) {
    gear.watchful()
    c.beginStorylet("Examine the books")
    c.chooseBranch("Search for a diary")
    c.onwards()
  }
  
  //2x strangling willow absinthe (1.0E), 1+ time passing
  def T7_dinner()(implicit c: Character) {
    gear.persuasive()
    c.beginStorylet("Dine with the ladies")
    c.chooseBranch("Charm the ladies")
    c.onwards()
  }
  
  //2x tale of terror, -wounds
  def DT7_dinner()(implicit c: Character) {
    gear.watchful()
    c.beginStorylet("Dine with the ladies")
    c.chooseBranch("Try all the dishes")
    c.onwards()
  }

  //70x jade, 30x nevercold brass, no Time Passing!
  def DT9_well_prep()(implicit c: Character) {
    gear.watchful()
    c.beginStorylet("Prepare to look down the well")
    c.chooseBranch("Equip yourself for a fishing expedition")  //dangerous
    c.onwards()
  }
  
  def DT9_well_fish()(implicit c: Character) {
    gear.dangerous()
    c.beginStorylet("Go to the well")
    c.chooseBranch("Fish in the well") 
    c.onwards()
  }
  
  //100x glim, stone tentacle key!
  def DT9_well_falls()(implicit c: Character) {
    gear.watchful()
    c.beginStorylet("Go to the well")
    c.chooseBranch("What falls down the well?")
    c.onwards()
  }
  
  //2x tale of terror
  def SDT9_well_prep()(implicit c: Character) {
    gear.persuasive()
    c.beginStorylet("Prepare to find out what the well has to offer")
    c.chooseBranch("Prepare yourself")
    c.onwards()
  }
  
  //1000x glim, lose dramatic tension
  def SDT9_well_net()(implicit c: Character) {
    gear.watchful()
    c.beginStorylet("Take your net to the well")
    c.chooseBranch("Trust Cynthia's advice?")
    c.onwards()
  }
  
  //+hedonist
  def T10_charades_lucy()(implicit c: Character) {
    c.beginStorylet("A game of charades")
    c.chooseBranch("The three Graces?")
    c.onwards()
  }
  
  //+nightmares, +dangerous, +daring
  def T10_charades_cynthia()(implicit c: Character) {
    c.beginStorylet("A game of charades")
    c.chooseBranch("The Furies?")
    c.onwards()
  }
  
  //1x sudden insight, 1x extraordinary implication (2.5E), 1x dramatic tension - resets to T3
  def T11_lucy()(implicit c: Character) {
    c.beginStorylet("Lucy finishes her story")
    c.chooseBranch()
    c.onwards()
  }
  
  //1x sudden insight, 1x extraordinary implication (2.5E), 1x dramatic tension - resets to T3
  def T11_cynthia()(implicit c: Character) {
    c.beginStorylet("Cynthia finishes her story")
    c.chooseBranch()
    c.onwards()
  }
}