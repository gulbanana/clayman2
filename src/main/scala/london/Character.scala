package london
import scala.xml._
import dispatch._, Defaults._

class Character(username: String, password: String) {
  private def site = url("http://fallenlondon.storynexus.com")
  private val http = new PipelinedClient()
  
  var actions = 17
  var actionCap = 20
  var location : Areas.Area = Areas.ShutteredPalace
  
  login()
  updateStatus()
  updateLocation()
  
  def travel(area: Areas.Area) {
    if (location != area) {
      parse_branches(http.query(site / "Map" / "Move" << Map("areaid" -> area.id.toString)))
      location = area
      println("Welcome to %s, delicious friend!".format(location.name))
    }
  }
  
  def onwards() {
    parse_branches(http.query(site / "Storylet" / "Available"))
    println("--> Onwards!")        
  }
  
  def logout() {
    http.dispose()
    println("Logged out.")
  }
  
  private def login() {
    http.command(site / "Auth" / "EmailLogin" << Map("emailAddress" -> username, "password" -> password))
    println("Logged in.")
  }
  
  private def updateStatus() {
    parseStatus(http.query(site / "Gap" / "Load" <<? Map("content" -> "/Me")),
                http.query(site / "Me"))
    println("An unknown gentleman.")
  }
  
  private def updateLocation() {
    parse_branches(http.query(site / "Storylet" / "Available"))
    println("Welcome to %s, delicious friend!".format(location.name))
  }
  
  private def parse_branches(soup: NodeSeq) = {
    val divs = soup \\ "div" filter {_.attribute("class").map(_.text == "storylet").getOrElse(false)}
    //println(divs)
  }
  
  private def parseEffects(soup: NodeSeq) = {
    val scripts = soup \\ "script"
    println(scripts)
  }
  
  private def parseStatus(outerSoup: NodeSeq, innerSoup: NodeSeq) = {
    
  }
}
