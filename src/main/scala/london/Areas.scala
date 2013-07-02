package london

object Areas {
  case class Area(id: Int, name: String)
  
  val Lodgings = Area(2, "your Lodgings")
  val LadybonesRoad = Area(4, "Ladybones Road")
  val WatchmakersHill = Area(5, "Watchmaker's Hill")
  val Veilgarden = Area(6, "Veilgarden")
  val Spite = Area(7, "Spite")
  val ForgottenQuarter = Area(9, "The Forgotten Quarter")
  val ShutteredPalace = Area(10, "The Shuttered Palace")
  val TheFlit = Area(11, "your Lodgings")
  val Carnival = Area(18, "Mrs Plenty's Carnival")
  val Court = Area(26, "The Empress' Court")
  val HouseOfChimes = Area(34, "The House of Chimes")
  
  private val byID = Map(
    2 -> Lodgings,
    4 -> LadybonesRoad,
    5 -> WatchmakersHill,
    6 -> Veilgarden,
    7 -> Spite,
    9 -> ForgottenQuarter,
    10 -> ShutteredPalace,
    11 -> TheFlit,
    18 -> Carnival,
    26 -> Court,
    34 -> HouseOfChimes
  )
  
  def apply(id: Int) = byID(id)
}