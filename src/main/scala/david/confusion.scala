package david
import api._
import common._

//The Royal Bethlehem Hotel, where you go when nightmares are too high
//There are all sorts of other options available with WTTS Stormy-Eyed, 
//which i haven't attempted to automate
object confusion extends MenaceArea { 
  private val blacklist = Map(
    "A shining nest" -> Discard, //+1 nightmares
    "A lost secret" -> Discard //search for missing secret is +lots!
  )
  
  private val goodViews = Set(
    "Think on the city" //-2 nightmares
  )
  
  private val playlist = Map(
    "A plan occurs" -> Hold, //fate to transfer to the Mirror Marches?
    "The view from your room" -> HoldUntil(_.branches.exists(goodViews), c => {
      c.chooseBranch(c.branches.find(goodViews).get)
    }),
    "Skeins of blood" -> Play //-nightmares, +dangerous
  )
  
  private val manager = Map(
    "The new cell" -> Play,
    "A fungal bloom" -> Play
  )
  
  val opportunities = new Opportunist(blacklist ++ playlist)
  
  //at least -2 nightmares and maybe -3
  def reduce_menace()(implicit c: Character) = {
    if (c.persuasive > c.watchful) {
      c.beginStorylet("Drink from the fountain in the lobby")
      c.chooseBranch("Take a deep gulp")
      c.onwards()
    } else {
      c.beginStorylet("Chat to the guests")
      c.chooseBranch("Reminisce about Fallen London")
      c.onwards()      
    }
  }
  
  val exitStorylets = Set("You return to your senses", "You are yourself again.")
}
