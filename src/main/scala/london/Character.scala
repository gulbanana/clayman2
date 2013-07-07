package london
import dispatch._, Defaults._

class Character(username: String, password: String) {
  private def site = url("http://fallenlondon.storynexus.com")
  private val http = new Session(username)
  private val parser = new Status()
  
  //Attempt to reuse a cookied session
  private var loginSoup = http.query(site / "Gap" / "Load" <<? Map("content" -> "/Me"))
  if (loginSoup.select("div#mainContentViaAjax").isEmpty) {
    http.command(site / "Auth" / "EmailLogin" << Map("emailAddress" -> username, "password" -> password))
    println("Logged in.")
    loginSoup = http.query(site / "Gap" / "Load" <<? Map("content" -> "/Me"))
  }
  
  parser updateStatus(loginSoup, http.query(site / "Me"))
  println("%s: %s.".format(parser.name, parser.description))
  
  parser updateBranches http.query(site / "Storylet" / "In")
  println("Welcome to %s, delicious friend!".format(location.name))
  
  //Public API
  def name = parser.name
  def actions = parser.actions
  def actionCap = parser.actionCap
  def location = parser.location
  def deck = parser.deck
  
  def watchful = parser.watchful
  def shadowy = parser.shadowy
  def dangerous = parser.dangerous
  def persuasive = parser.persuasive
  
  def items = parser.items
  def qualities = parser.qualities
  def opportunities = parser.opportunityIDs.keySet
  def storylets = parser.eventIDs.keySet
  
  // MANAGE STATUS: change location, equipment
  def travel(area: Area) = if (parser.updateLocation(area)) {
    parser updateBranches http.query(site / "Map" / "Move" << Map("areaid" -> area.id.toString))
    println("Welcome to %s, delicious friend!".format(area.name))      
  }
  
  def equip(item: String) = if (parser.unequipped.exists(_.name==item)) {
    parser updateEquipment http.query(site / "Me" / "AdoptThing" << Map("id" -> parser.itemIDs(item).toString))    
    println("Equipped [%s].".format(item))
  }
  
  def unequip(item: String) = if (parser.equipped.exists(_.name==item)) {
    parser updateEquipment http.query(site / "Me" / "UnadoptThing" << Map("id" -> parser.itemIDs(item).toString))
    println("Unequipped [%s].".format(item))
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
  def playOpportunity(opportunity: String) {
    parser updateBranches http.query(site / "Storylet" / "Begin" << Map("eventid" -> parser.opportunityIDs(opportunity).toString))
    println("\"%s\"".format(parser.title))
  }
  
  def useItem(item: String) {
    parser updateBranches http.query(site / "Storylet" / "UseQuality" << Map("qualityId" -> parser.itemIDs(item).toString))
    println("\"%s\"".format(parser.title))
  }
  
  
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
    if (parser.branchIDs.size != 1) throw new Exception("Requires exactly one branch. There are %d branches available.".format(parser.branchIDs.size))
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
