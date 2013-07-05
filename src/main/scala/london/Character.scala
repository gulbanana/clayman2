package london
import dispatch._, Defaults._

class Character(username: String, password: String) {
  private def site = url("http://fallenlondon.storynexus.com")
  private val http = new Session()
  private val parser = new Status()
  
  http.command(site / "Auth" / "EmailLogin" << Map("emailAddress" -> username, "password" -> password))
  println("Entered the Neath.")
  
  parser updateStatus(http.query(site / "Gap" / "Load" <<? Map("content" -> "/Me")), http.query(site / "Me"))
  println("%s: %s.".format(parser.name, parser.description))
  
  parser updateBranches http.query(site / "Storylet" / "In")
  println("Welcome to %s, delicious friend!".format(location.name))
  
  def name = parser.name
  def actions = parser.actions
  def actionCap = parser.actionCap
  def location = parser.location
  def watchful = parser.watchful
  def shadowy = parser.shadowy
  def dangerous = parser.dangerous
  def persuasive = parser.persuasive
  def items = parser.items
  def qualities = parser.qualities
  
  // MANAGE STATUS: change location, equipment
  def travel(area: Areas.Area) {
    if (parser.updateLocation(area)) {
      parser updateBranches http.query(site / "Map" / "Move" << Map("areaid" -> area.id.toString))
      println("Welcome to %s, delicious friend!".format(area.name))      
    } 
  }
  
  def equip(item: String) {
    println(http.query(site / "Me" / "AdoptThing" << Map("id" -> parser.equipmentIDs(item).toString)))
  }
  
  def unequip(item: String) {
    println(http.query(site / "Me" / "UnadoptThing" << Map("id" -> parser.equipmentIDs(item).toString)))
  }
  
  // MANAGE OPPORTUNITY DECK: draw x, discard 
  def drawOpportunities() {
    parser updateOpportunities http.query(site / "Storylet" / "DrawOpportunities")
  }
  
  def discardOpportunity(opportunity: String) {
    parser updateOpportunities http.query(site / "Storylet" / "DiscardSometimesCard" <<? Map("eventid" -> parser.opportunityIDs(opportunity).toString))
    println ("Discarded \"%s\"".format(opportunity))
  }
  
  // BEGIN STORYLETS: opportunities, items, or area-based 
  def opportunities = parser.opportunityIDs.keySet
  def playOpportunity(opportunity: String) {
    parser updateBranches http.query(site / "Storylet" / "Begin" << Map("eventid" -> parser.opportunityIDs(opportunity).toString))
    println("\"%s\"".format(parser.title))
  }
  
  def useItem(item: String) {
    parser updateBranches http.query(site / "Storylet" / "UseQuality" << Map("qualityId" -> parser.itemIDs(item).toString))
    println("\"%s\"".format(parser.title))
  }
  
  def storylets = parser.eventIDs.keySet
  def beginStorylet(storylet: String) {
    parser updateBranches http.query(site / "Storylet" / "Begin" << Map("eventid" -> parser.eventIDs(storylet).toString))
    println("\"%s\"".format(parser.title))
  }
  
  // IN STORYLETS: choose a branch, onwards, or back
  def branches = parser.branchIDs.keySet
  def chooseBranch(branch: String) {
    val soup = http.query(site / "Storylet" / "ChooseBranch" << Map("branchid" -> parser.branchIDs(branch).toString,
                                                                              "secondChances" -> "false"))
    println("--> %s".format(branch))
    parser updateEffects soup
  }
  
  def chooseBranch() {
    if (parser.branchIDs.size != 1) throw new Exception("More than one branch is available")
    chooseBranch(parser.branchIDs.head._1)
  }
  
  def perhapsNot() {
    parser updateBranches http.query(site / "Storylet" / "GoBackFromStorylet")
    println("--> Perhaps not.")    
  }
  
  def onwards() {
    parser updateBranches http.query(site / "Storylet" / "Available")
    println("--> Onwards!")
  }
}
