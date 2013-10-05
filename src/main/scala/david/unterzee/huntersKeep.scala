package david.unterzee
import api._
import common._
import david._

//all actions give +2cp time passing 
//at most stages you can choose from a selection of >1E worth of lower-tier items, or 1E of higher-tier - sometimes more
//lucy = lucina - juno?? Eileithyia/Ilithyia
object huntersKeep extends RepeatedJob {
  def apply(implicit c: Character) = if (c.qualities("Time Passing in the Southern Archipelago") < 2) {
    keep.T1_house(); true
  } else if (c.qualities("Time Passing in the Southern Archipelago") < 3) {
    keep.T2_well(); true
  } else if (c.qualities("Dramatic Tension") < 1) {
    tensionless
  } else {
    tension
  }
  
  def tensionless(implicit c: Character) = did (c.qualities("Time Passing in the Southern Archipelago") < 7) {
    keep.T3_lucy()
  } or (c.qualities("Time Passing in the Southern Archipelago") < 10) {
    if (c.wounds > 0)
      keep.T7_lucy()
    else 
      keep.T7_books()
  } or (c.qualities("Time Passing in the Southern Archipelago") < 11) {
    keep.T10_charades_lucy()
  } or (c.qualities("Time Passing in the Southern Archipelago") == 11) {
    keep.T11_lucy()
  }
  
  def tension(implicit c: Character) = did (c.qualities("Time Passing in the Southern Archipelago") < 7) {
    if (c.nightmares > 0)
      keep.DT3_phoebe()
    else 
      keep.DT3_lucy()
  } or (c.qualities("Time Passing in the Southern Archipelago") < 9) {
    if (c.wounds > 0)
      keep.DT7_dinner()
    else
      keep.DT7_cynthia()
  } or (c.qualities("Time Passing in the Southern Archipelago") < 10) {
    if (c.items("Stone Tentacle-Key") < 1)
      keep.DT9_well_falls()
    else 
      keep.SDT9_well_net()
  }
  //with DT9 and no key, you can get a key; with DT9 and a key you can lose DT but gain 1000 glim  
}