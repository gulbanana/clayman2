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
  
  def travel(area: Areas.Area) {
    if (parser.updateLocation(area)) {
      parser updateBranches http.query(site / "Map" / "Move" << Map("areaid" -> area.id.toString))
      println("Welcome to %s, delicious friend!".format(area.name))      
    } 
  }
  
  def useItem(item: String) {
    parser updateBranches http.query(site / "Storylet" / "UseQuality" << Map("qualityId" -> parser.itemIDs(item).toString))
    println("\"%s\"".format(parser.title))
  }
  
  def storyAvailable(storylet: String) = parser.eventIDs.keySet.contains(storylet)
  def beginStory(storylet: String) {
    parser updateBranches http.query(site / "Storylet" / "Begin" << Map("eventid" -> parser.eventIDs(storylet).toString))
    println("\"%s\"".format(parser.title))
  }
  
  def branchAvailable(branch: String) = parser.branchIDs.keySet.contains(branch)
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
