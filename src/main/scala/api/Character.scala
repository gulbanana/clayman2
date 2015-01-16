package api
import dispatch._, Defaults._

object Character {
  val StatCap = 200 
}

class Character(username: String, password: String) {
  private def site = url("http://fallenlondon.storynexus.com")
  private val http = new Session(username)
  private val parser = new Status()
  
  //Attempt to reuse a cookied session
  private var loginSoup = http.query(site / "Gap" / "Load" <<? Map("content" -> "/Me"))
  private var newLogin = false
  if (loginSoup.select("div#mainContentViaAjax").isEmpty) {
    http.command(site / "Auth" / "EmailLogin" << Map("emailAddress" -> username, "password" -> password, "rememberMe" -> "true"))
    newLogin = true
    loginSoup = http.query(site / "Gap" / "Load" <<? Map("content" -> "/Me"))
  }
  
  parser updateStatus loginSoup
  
  parser updateEquipment http.query(site / "Me" / "InventoryExpanded")
  
  private val meSoup = http.query(site / "Me")
  parser updateDescription meSoup
  parser updateEquipment meSoup
  parser updateItems meSoup
  
  //XXX change this to dynamically read the quality categories?
  parser updateQualities("Acquaintance", http.query(site / "Me" / "StatusesForCategory" <<? Map("category" -> "Acquaintance")))
  parser updateQualities("Contacts", http.query(site / "Me" / "StatusesForCategory" <<? Map("category" -> "Contacts")))
  parser updateQualities("Menace", http.query(site / "Me" / "StatusesForCategory" <<? Map("category" -> "Menace")))
  parser updateQualities("Progress", http.query(site / "Me" / "StatusesForCategory" <<? Map("category" -> "Progress")))
  parser updateQualities("Quirk", http.query(site / "Me" / "StatusesForCategory" <<? Map("category" -> "Quirk")))
  parser updateQualities("Story", http.query(site / "Me" / "StatusesForCategory" <<? Map("category" -> "Story")))
  
  parser updateBranches http.query(site / "Storylet" / "In")
  
  if (newLogin)
    println("%s: %s.".format(parser.name, parser.description))
  
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
  
  def nightmares = parser.qualities("Nightmares")
  def suspicion = parser.qualities("Suspicion")
  def wounds = parser.qualities("Wounds")
  def scandal = parser.qualities("Scandal")
  
  def items = parser.items
  def qualities = parser.qualities
  def opportunities = parser.opportunityIDs.keySet
  def storylets = parser.eventIDs.keySet
  def branches = parser.branchIDs.keySet
  def invitations= parser.invitationIDs.keySet
  
  def opportunityCap = parser.opportunityCap
  
  // MANAGE STATUS: change location, equipment
  def travel(area: Area) = if (parser.updateLocation(area)) {
    parser updateBranches http.query(site / "Map" / "Move" << Map("areaid" -> area.id.toString))
    println("Welcome to %s, delicious friend!".format(area.name))      
  }
  
  def equip(item: String) = if (parser.unequipped.exists(_.name==item)) {
    parser updateSingleSlot http.query(site / "Me" / "AdoptThing" << Map("id" -> parser.itemIDs(item).toString))
    println("Equipped [%s].".format(item))
  }
  
  def unequip(item: String) = if (parser.equipped.exists(_.name==item)) {
    parser updateSingleSlot http.query(site / "Me" / "UnadoptThing" << Map("id" -> parser.itemIDs(item).toString))
    println("Unequipped [%s].".format(item))
  }
  
  // MANAGE OPPORTUNITY DECK: draw x, discard 
  def drawOpportunities() {
    parser updateOpportunities http.query(site / "Storylet" / "DrawOpportunities")
  }
  
  def discardOpportunity(opportunity: String) {
    parser updateOpportunities http.query(site / "Storylet" / "DiscardSometimesCard" <<? Map("eventid" -> parser.opportunityIDs(opportunity).toString))
    println ("Discarded \"%s\".".format(opportunity))
  }
  
  // MANAGE SOCIAL ACTIONS: context free
  def checkMessages() {
    parser updateInvitations http.query(site / "Me" / "Landing")
  }
  
  def acceptInvitation(message: String) {
    parser updateInvitations http.query(site / "Me" / "AcceptInvitation" << Map("invitationId" -> parser.invitationIDs(message).toString))
  }
  
  def rejectInvitation(message: String) {
    parser updateInvitations http.query(site / "Me" / "RejectInvitation" << Map("invitationId" -> parser.invitationIDs(message).toString))
  }
  
  // BEGIN STORYLETS: opportunities, items, or area-based 
  def playOpportunity(opportunity: String) {
    parser updateBranches http.query(site / "Storylet" / "Begin" << Map("eventid" -> parser.opportunityIDs(opportunity).toString))
    println("\"%s\"".format(parser.title))
  }
  
  def useItem(item: String) {
    if (!parser.itemIDs.keySet.contains(item))
      parser updateItems http.query(site / "Me")
    
    parser updateBranches http.query(site / "Storylet" / "UseQuality" << Map("qualityId" -> parser.itemIDs(item).toString))
    println("\"%s\"".format(parser.title))
  }
  
  def beginStorylet(storylet: String) {
    if (!parser.eventIDs.keySet.contains(storylet))
      parser updateBranches http.query(site / "Storylet" / "Available")
    	
    if (!parser.eventIDs.keySet.contains(storylet))
      throw new DisobedientException("No such storylet \"" + storylet + "\"")
      
    parser updateBranches http.query(site / "Storylet" / "Begin" << Map("eventid" -> parser.eventIDs(storylet).toString))
    println("\"%s\"".format(parser.title))
  }
  
  // IN STORYLETS: choose a branch, onwards, or back
  def currentStorylet = parser.title
  
  def chooseBranch(branch: String, useSecondChance: Boolean = false) {
    if (!parser.branchIDs.keySet.contains(branch))
      parser updateBranches http.query(site / "Storylet" / "In")
    
    if (!parser.branchIDs.keySet.contains(branch)) {
      perhapsNot()
      throw new DisobedientException("No such branch \"" + branch + "\"")
    }
      
    var soup = http.query(site / "Storylet" / "ChooseBranch" << Map("branchid"      -> parser.branchIDs(branch).toString,
                                                                    "secondChances" -> useSecondChance.toString))
    println("--> %s".format(branch))
                                                                    
    if (useSecondChance && branches.contains(branch)) {
      soup = http.query(site / "Storylet" / "ChooseBranch" << Map("branchid"      -> parser.branchIDs(branch).toString))
      println("--> %s (second chance)".format(branch))
    }
    
    parser updateEffects soup
  }
  
  def chooseBranch() {
    if (parser.branchIDs.size != 1) throw new DisobedientException("Requires exactly one branch. There are %d branches available.".format(parser.branchIDs.size))
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
