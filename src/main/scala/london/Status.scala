package london
import collection.JavaConversions._
import org.jsoup.nodes._

class Status {
  var actions = 0
  var actionCap = 10
  var description = ""
  var items = Map[String, Int]()
  var location = Areas.ShutteredPalace
  var name = ""
  var title = ""
    
  var itemIDs = Map[String, Int]()
  var eventIDs = Map[String, Int]()
  var branchIDs = Map[String, Int]()
  
  def updateLocation(area: Areas.Area) =  if (location != area) {
    location = area
    true
  } else {
    false
  }
  
  def updateBranches(soup: Document) = {
    title = soup.select("h3").text
    
    eventIDs = (for (storylet <- soup.select("div.storylet") if storylet.children.size > 1) yield {
      val key = storylet.select(".storylet_rhs > h2").text
      val id = storylet.select("input").attr("onclick").drop(11).dropRight(2).toInt
      key -> id
    }).toMap
    
    branchIDs = (for (branch <- soup.select("div.storylet > form")) yield {
      val key = branch.select(".storylet_rhs > h5").text
      val id = branch.attr("onsubmit").drop(64).split(',').head.toInt
      key -> id
    }).toMap    
  }
  
  def updateEffects(soup: Document) = {
    //val scripts = soup.select("script")
    //println(soup)
  }
  
  def updateStatus(outerSoup: Document, innerSoup: Document) = {
    
  }
}