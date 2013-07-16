package london
import collection.JavaConversions._
import org.jsoup.Jsoup, org.jsoup.nodes._

case class Item(id: Int, name: String, quantity: Int)

class Status {
  var name = ""
  var description = ""
  
  var actions = 0
  var actionCap = 10
  
  var location:Area = null
  
  var deck = 0
  
  var watchful = 0
  var shadowy = 0
  var dangerous = 0
  var persuasive = 0
  
  var qualities = Map[String, Int]()
  var items = Map[String, Int]()
  var equipped = Seq[Item]()
  var unequipped = Seq[Item]()
  
  var title = ""
  
  var opportunityIDs = Map[String, Int]()
  var eventIDs = Map[String, Int]()
  var branchIDs = Map[String, Int]()
  var itemIDs = Map[String, Int]()
  var invitationIDs = Map[String, Int]()

  private val areaPattern = """(?s).*updatePageAfterStoryletChoice\((\d+),.*""".r
  private val actionPattern = """(?s).*setActionsLevel\((\d+),.*""".r

  private val successPattern = """You succeeded in a (.+) challenge!.*""".r
  private val failurePattern = """(.+) (\d+) failed in a challenge!.*""".r

  private val itemModPattern = """You've (gained|lost) (\d+) x (.+) \(new total (\d+)\).""".r
  private val itemSetPattern = """You now have (\d+) x (.+)\.?""".r
  private val itemSetPattern2 = """You now have (\d+) of this: '(.+)'.""".r
  private val itemClearPattern = """You no longer have any of this: '(.+)'.""".r
  
  private val qualityModPattern = """(.+) has (increased|dropped) to (\d+)!""".r
  private val qualityModPattern2 = """(.+) has (increased|dropped) to (\d+) - (.+)!""".r
  private val qualitySetPattern = """An occurrence! Your '(.+)' quality is now (\d+)!""".r
  private val qualityClearPattern = """You've lost a quality: (.+).""".r
  private val qualityChangingPattern = """(.+) is (increasing|dropping)...""".r
  private val qualityNoChangePattern = """(.+) hasn't changed, because it's higher than (\d+)""".r
    
  private val beginVenturePattern = """(.+) shows your progress in the venture.""".r
  private val endVenturePattern = """(.+) has been reset: a conclusion, or a new beginning?""".r
  
  private val travelPattern = """You have moved to a new area: (.+)""".r
  
  //ways of the neath:
  //A bat zips past, not far overhead.
  //Today, something in the air makes the gas-lamps slink low, burn marsh-green.
  
  def updateEffects(soup: Document) = {
    val updateScript = soup.select("script")(1).data
    val areaPattern(newArea) = updateScript 
    val actionPattern(newActions) = updateScript
    location = Areas(newArea.toInt)
    actions = newActions.toInt

    for (effect <- soup.select("div.quality_update_box p").map(_.text)) {
      println("    %s".format(effect))
        
      effect match {
        case successPattern(qname) => ()
        case failurePattern(qname, qval) => ()
        
        case itemModPattern(idir, imod, iname, ival) => items = items.updated(iname, ival.toInt)
        case itemSetPattern(iname, ival) => items = items.updated(iname, ival.toInt)
        case itemSetPattern2(iname, ival) => items = items.updated(iname, ival.toInt)
        case itemClearPattern(iname) => items = items.updated(iname, 0)
        
        case qualityModPattern(qname, qdir, qval) => qualities = qualities.updated(qname, qval.toInt)
        case qualityModPattern2(qname, qdir, qval, qdesc) => qualities = qualities.updated(qname, qval.toInt)
        case qualitySetPattern(qname, qval) => qualities = qualities.updated(qname, qval.toInt)
        case qualityClearPattern(qname) => qualities = qualities.updated(qname, 0)
        case qualityChangingPattern(qname, qdir) => ()
        case qualityNoChangePattern(qname, qmax) => ()
        
        case beginVenturePattern(qname) => qualities = qualities.updated(qname, 1)
        case endVenturePattern(qname) => qualities = qualities.updated(qname, 0)
        
        case travelPattern(aname) => () //XXX make this do a thing - but maybe unnecessary, b/c of javascript
        
        case unknown:String => println("Effect NOT MATCHED: " + unknown)
      }
    }
  }
  
  def updateStatus(outerSoup: Document, innerSoup: Document) = {
    //Actions: nested spans in the outer HTML
    val actionPattern = """(\d+)/(\d+)""".r
    val actionPattern(current, max) = outerSoup.select("span.actions_remaining").text
    actions = current.toInt
    actionCap = max.toInt

    //Location: part of a script tag which highlights that area on the map 
    val areaPattern = """(?s).*CurrentArea\((\d+),.*""".r
    val areaPattern(areaID) = outerSoup.select("div#currentAreaSection > script").first.data
    location = Areas(areaID.toInt)
    
    //Main stats: outer HTML, in a set of span pairs  
    watchful = extractStat(outerSoup, 209)
    shadowy = extractStat(outerSoup, 210)
    dangerous = extractStat(outerSoup, 211)
    persuasive = extractStat(outerSoup, 212)
    
    //Name and description: at the start of the /Me page in redesign_heading div
    name = innerSoup.select("div.redesign_heading > h1 > a").first.text
    description = innerSoup.select("div.redesign_heading > p").first.text
    
    //Non-item qualities: <strong> tags in you_bottom_lhs div
    val qualityPattern = """(.*) (\d+).*""".r
    qualities = (for (quality <- innerSoup.select("div.you_bottom_lhs strong") if quality.text.matches(qualityPattern.toString)) yield {
      val qualityPattern(name, quantity) = quality.text
      name -> quantity.toInt
    }).toMap.withDefaultValue(0)
    
    //item qualities: the link-containing slots in various divs 
    updateEquipment(innerSoup) //used standalone for tracking
    val carried = extractItems(innerSoup.select("div.you_bottom_rhs li > a.tooltip"))
    val possessions = carried ++ unequipped ++ equipped
    
    itemIDs = possessions.map(i => i.name -> i.id).toMap
    items = possessions.map(i => i.name -> i.quantity).toMap.withDefaultValue(0)
  }
  
  def updateEquipment(soup: Document) {
    unequipped = extractItems(soup.select("div.you_mid_rhs li > a.tooltip"))
    equipped = extractItems(soup.select("div.you_mid_mid li > a.tooltip"))
  }
  
  def updateLocation(area: Area) =  if (location != area) {
    location = area
    true
  } else {
    false
  }
  
  val deckPattern = """(?s).*(\d+) cards? waiting.*""".r
  def updateOpportunities(soup: Document) = {
    opportunityIDs = (for (opportunity <- soup.select("ul#cards > li:not(.card_deck) a")) yield {
      val key = Jsoup.parseBodyFragment(opportunity.attr("title")).select("strong").text.trim
      val id = opportunity.select("input[type=image]").attr("onclick").drop(11).dropRight(2).toInt
      key -> id
    }).toMap
    
    soup.select("li.card_deck").text match {
      case deckPattern(cards) => deck = cards.toInt
      case _ => deck = 0
    }
  }
  
  def updateBranches(soup: Document) = {
    title = soup.select("h3").text
    
    //disabled: <input type="button" onclick="return false;" class="standard_btn greyed" style="float: right; min-width: 80px!important;" value="Locked"> 
    //enabled: <input name="" type="button" value="GO" class="standard_btn " onclick="beginEvent(14648);" style="min-width:80px!important;">
    eventIDs = (for (
      storylet <- soup.select("div.storylet") 
      if storylet.children.size > 1 
      && !storylet.select("input[value]").isEmpty
      && storylet.select("input").attr("value") != "Locked"
    ) yield {
      val key = storylet.select(".storylet_rhs > h2").text
      val id = storylet.select("input").attr("onclick").drop(11).dropRight(2).toInt
      key -> id
    }).toMap
    
    branchIDs = (for (branch <- soup.select("div.storylet > form")) yield {
      val key = branch.select(".storylet_rhs > h5").text
      val id = branch.attr("onsubmit").drop(64).split(',').head.toInt
      key -> id
    }).toMap    
    
    updateOpportunities(soup)
  }
  
  def updateInvitations(soup: Document) {
    //invitationIDs = 
  }
  
  val tooltipPattern = """<strong>(\d+) x (.*?)<.*""".r
  val imagePattern = """infoBarQImage(\d*)""".r
  private def extractItems(elements: Seq[Element]) = for (item <- elements) yield {
    val tooltipPattern(quantity, name) = item.attr("title") 
    val imagePattern(id) = item.select("div").last.attr("id")
    Item(id.toInt, name, quantity.toInt)
  }
  
  private def extractStat(soup: Document, id: Int) = {
    var level = soup.select("span#infoBarQLevel"+id).text
    var bonus = soup.select("span#infoBarBonusPenalty"+id).text
    
    if (bonus != "")
      level.toInt + bonus.replace("+", "").toInt
    else
      level.toInt
  }
}
