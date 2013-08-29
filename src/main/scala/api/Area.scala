package api

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
  val NewNewgate = Area(16, "New Newgate Prison - again!")
  val TombColonies = Area(17, "The Tomb-Colonies")
  val Carnival = Area(18, "Mrs Plenty's Carnival")
  val University = Area(23, "The University")
  val Court = Area(26, "The Empress' Court")
  val LabyrinthOfTigers = Area(28, "The Labyrinth of Tigers")
  val MahoganyHall = Area(30, "Mahogany Hall")
  val BazaarSideStreets = Area(31, "Bazaar Side-streets")
  val HouseOfChimes = Area(34, "The House of Chimes")
  val BroadUnterzee = Area(36, "the Broad Unterzee")
  val WilmotsEnd = Area(42, "Wilmot's End")
  val DoubtStreet = Area(47, "Doubt Street")
  val GamekeepersCottage = Area(5821, "The Gamekeeper's Cottage")
  
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
    17 -> TombColonies,
    18 -> Carnival,
    23 -> University,
    26 -> Court,
    28 -> LabyrinthOfTigers,
    30 -> MahoganyHall,
    31 -> BazaarSideStreets,
    34 -> HouseOfChimes,
    36 -> BroadUnterzee,
    42 -> WilmotsEnd,
    47 -> DoubtStreet,
    5821 -> GamekeepersCottage
  ) withDefault(i => { println("WARNING: unknown areaid %d".format(i)); Area(i, "Unknown Area")})
  
  def apply(id: Int) = byID(id)
}
