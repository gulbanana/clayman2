package london

case class Area(id: Int, name: String)

object Areas {
  val Lodgings = Area(2, "your Lodgings")
  val WolfstackDocks = Area(3, "Wolfstack Docks")
  val LadybonesRoad = Area(4, "Ladybones Road")
  val WatchmakersHill = Area(5, "Watchmaker's Hill")
  val Veilgarden = Area(6, "Veilgarden")
  val Spite = Area(7, "Spite")
  val ForgottenQuarter = Area(9, "The Forgotten Quarter")
  val ShutteredPalace = Area(10, "The Shuttered Palace")
  val TheFlit = Area(11, "The Flit")
  val NewNewgate = Area(16, "New Newgate Prison")
  val Carnival = Area(18, "Mrs Plenty's Carnival")
  val Court = Area(26, "The Empress' Court")
  val HouseOfChimes = Area(34, "The House of Chimes")
  
  private val byID = Map(
    2 -> Lodgings,
    3 -> WolfstackDocks,
    4 -> LadybonesRoad,
    5 -> WatchmakersHill,
    6 -> Veilgarden,
    7 -> Spite,
    9 -> ForgottenQuarter,
    10 -> ShutteredPalace,
    11 -> TheFlit,
    16 -> NewNewgate,
    18 -> Carnival,
    26 -> Court,
    34 -> HouseOfChimes
  )
  
  def apply(id: Int) = byID(id)
}
