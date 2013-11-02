package david
import api._
import common._
import david._

object main extends BufferedJob {
  val menaceEntries = Set("A sudden darkness!", "Imprisoned! Again!", "A maelstrom of scandal!", "You have lost your mind!")
  
  def apply(implicit c: Character) = if (menaceEntries(c.currentStorylet)) {    
    println("\"%s\"".format(c.currentStorylet))
    c.chooseBranch()
    c.onwards()
    true
  } else c.location match {
    case Areas.NewNewgate     => prison(c)
    case Areas.TombColonies   => colonies(c)
    case Areas.SlowBoat       => river(c) 
    case Areas.RoyalBethlehem => confusion(c)
    case Areas.MirrorMarches  => marches(c)
    case Areas.BroadUnterzee  => unterzee.southernArchipelago(c)
    case Areas.SeaOfVoices    => unterzee.seaOfVoices(c)
    case Areas.BullboneIsland => unterzee.bullboneIsland(c)
    case Areas.GruntingFen    => unterzee.gruntingFen(c)
    case Areas.HuntersKeep    => unterzee.huntersKeep(c)
    case Areas.Polythreme     => polythreme.grind(c)
    case x if Areas.london(x) => london.standardGrind(c)
    case _                    => false
  } 
}