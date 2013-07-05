package london
import dispatch._, Defaults._

class Character(username: String, password: String) {
  private def site = url("http://fallenlondon.storynexus.com")
  private val http = new Session()
  private val parser = new Status()
  
  def actions = parser.actions
  def actionCap = parser.actionCap
  def description = parser.description
  def items = parser.items
  def location = parser.location
  def name = parser.name
  
  http.command(site / "Auth" / "EmailLogin" << Map("emailAddress" -> username, "password" -> password))
  println("Entered the Neath.")
  parser updateStatus(http.query(site / "Gap" / "Load" <<? Map("content" -> "/Me")), http.query(site / "Me"))
  println("An unknown gentleman.")
  parser updateBranches http.query(site / "Storylet" / "In")
  println("Welcome to %s, delicious friend!".format(location.name))
  
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
  
  def beginStory(storylet: String) {
    parser updateBranches http.query(site / "Storylet" / "Begin" << Map("eventid" -> parser.eventIDs(storylet).toString))
    println("\"%s\"".format(parser.title))
  }
  
  def chooseBranch(branch: String) {
    parser updateEffects http.query(site / "Storylet" / "ChooseBranch" << Map("branchid" -> parser.branchIDs(branch).toString,
                                                                              "secondChances" -> "false"))
    println("--> %s".format(parser.title))
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
