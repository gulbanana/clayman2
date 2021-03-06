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
  val RoyalBethlehem = Area(13, "a state of some confusion")
  val SlowBoat = Area(14, "a slow boat passing a dark beach on a silent river")
  val NewNewgate = Area(16, "New Newgate Prison - again!")
  val TombColonies = Area(17, "The Tomb-Colonies")
  val Carnival = Area(18, "Mrs Plenty's Carnival")
  val MirrorMarches = Area(22, "The Mirror-Marches")
  val University = Area(23, "The University")
  val Court = Area(26, "The Empress' Court")
  val LabyrinthOfTigers = Area(28, "The Labyrinth of Tigers")
  val ShadowsOfTheOrphanage = Area(29, "the shadows of the Orphanage")
  val MahoganyHall = Area(30, "Mahogany Hall")
  val BazaarSideStreets = Area(31, "Bazaar Side-streets")
  val RoadsBeneath = Area(33, "Roads Beneath")
  val HouseOfChimes = Area(34, "The House of Chimes")
  val FluteStreet = Area(35, "Flute Street")
  val BroadUnterzee = Area(36, "the Broad Unterzee")
  val HuntersKeep = Area(37, "Hunter's Keep")
  val WilmotsEnd = Area(42, "Wilmot's End")
  val TheOrphanage = Area(43, "The Silent Corridors of the Orphanage")
  val ForeignOffice = Area(45, "The Foreign Office")
  val DoubtStreet = Area(47, "Doubt Street")
  val BullboneIsland = Area(48, "Bullbone Island")
  val CorpsecageIsland = Area(49, "Corpsecage Island")
  val GruntingFen = Area(50, "Grunting Fen")
  val SeaOfVoices = Area(51, "The Sea of Voices")
  val Polythreme = Area(53, "Polythreme Streets")
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
    13 -> RoyalBethlehem,
    14 -> SlowBoat,
    16 -> NewNewgate,
    17 -> TombColonies,
    18 -> Carnival,
    22 -> MirrorMarches,
    23 -> University,
    26 -> Court,
    28 -> LabyrinthOfTigers,
    29 -> ShadowsOfTheOrphanage,
    30 -> MahoganyHall,
    31 -> BazaarSideStreets,
    33 -> RoadsBeneath,
    34 -> HouseOfChimes,
    35 -> FluteStreet,
    36 -> BroadUnterzee,
    37 -> HuntersKeep,
    42 -> WilmotsEnd,
    43 -> TheOrphanage,
    45 -> ForeignOffice,
    47 -> DoubtStreet,
    48 -> BullboneIsland,
    49 -> CorpsecageIsland,
    50 -> GruntingFen,
    51 -> SeaOfVoices,
    53 -> Polythreme,
    5821 -> GamekeepersCottage
  ) withDefault(i => { println("WARNING: unknown areaid %d".format(i)); Area(i, "Unknown Area")})
  
  def apply(id: Int) = byID(id)
  
  val London = Set(
    BazaarSideStreets,
    Carnival,
    DoubtStreet,
    ForeignOffice,
    ForgottenQuarter,
    GamekeepersCottage,
    HouseOfChimes,
    LabyrinthOfTigers,
    LadybonesRoad,
    Lodgings,
    MahoganyHall,
    ShutteredPalace,
    Spite,
    TheFlit,
    University,
    Veilgarden,
    WatchmakersHill,
    WilmotsEnd,
    WolfstackDocks
  ) 
}
