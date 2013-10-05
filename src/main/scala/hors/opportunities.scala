package hors
import common._

object cards {
  val standard = Map(
    "Academic discipline" -> Discard,   //dangerous 45
    "Moonish water" -> Discard,         //dangerous 57
    "After dinner speaking" -> Discard, //persuasive 33
    "The Windward Tower" -> Hold        //lodgings
  )
  
  val story = Map(
    "The independence of young ladies" -> Hold //clathermont 1
  )
    
  val social = Map(
    "Pass the Cat: a wriggling delivery" -> Hold,
    "Give a Gift! A commotion in the Square of Lofty Words" -> Hold
  )
}

object opportunities extends Opportunist(cards.standard ++ cards.story ++ cards.social) {
}