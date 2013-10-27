package david
import api._
import common._

// parabola?
object marches extends MenaceArea { 
  private val blacklist = Map(
    "Orange lilies"  -> Discard, //+2 nightmares
    "A glimpse of brass" -> Discard //-1 nightmares
  )
  
  //"Heavenly fruits" plum +nightmares, peckish
  //"A green vine" 50% nothing
  
  private val playlist = Map(
    "Night is falling" -> Play,                                      //-2+ nightmares (4?)
    "A striped cat" -> Play,                                         //-3 nightmares, +1 persuasive
    "A black cat" -> Play,                                           //-? nightmares (3?), +1 shadowy
    "A spotted cat" -> Play,                                         //-3 nightmares (?), +shadowy
    "A golden cat" -> Play,                                          //-3 nightmares, +watchful
    "A glimpse of debauchery" -> Play,                               //-3 nightmares, conn: bohemian
    "A glimpse of a church" -> Play,                                 //-3 nightmares, conn: church
    "A glimpse of a cat" -> Play,                                    //-3 nightmares
    "A glimpse of a clock" -> Play,                                  //-2 nightmares, +dangerous
    "A glimpse of amber" -> Play,                                    //-2 nightmares, conn: rubbery
    "A glimpse of a kiss" -> Play,                                   //-3 nightmares, +hedonist
    "A glimpse of silver" -> Play,                                   //-2+ nightmares
    "A glimpse of a tavern" -> Play,                                 //-3+ nightmares
    "A tree of scars" -> Play("Read the markings, if you know how"), //-5 nightmares, stteoi
    "A bird-of-paradise" -> Play("Follow it")                        //-2 nightmares, tbf
  )
  
  val opportunities = new Opportunist(blacklist ++ playlist)
  
  def reduce_menace()(implicit c: Character) {
    // mirror frames
    //brass frame (nm 3): -3, +1 scandal
    //silver frame (nm 4)
    //iron frame (nm 6): -1, +1 wounds
    // jungle
    //the stream: 50% -5(or 4?), 50% +2
    //the scent of roses: -1
    // temple complex
    //enticed: -1, +wtfc, +stteoi, +watchful
    //stay away: +nightmare,s tbf
    c.beginStorylet("The temple complex")
    c.chooseBranch("Be enticed by the temples")
    c.onwards()
  }
  
  val exitStorylets = Set("A familiar darkness")
}
