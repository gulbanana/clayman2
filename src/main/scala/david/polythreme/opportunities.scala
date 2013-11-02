package david.polythreme

import common._

object opportunities extends Opportunist(cards.blacklist)

object cards {
  val blacklist = Map("This card does not really exist" -> Discard)
}

//The Wax Wind Comes: randomly fascinating or investigating, 3-5
//At the Market
//  Attract attention at the market: +5-10 fascinating
//  Wander around the market: -10 fascinating, +20 surface-silk
//The Masked Man: >50% +5 fascinating and investigating