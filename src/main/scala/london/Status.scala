package london
import collection.JavaConversions._
import org.jsoup.nodes._

class Status {
  var name = ""
  var description = ""
  
  var actions = 0
  var actionCap = 10
  
  var location:Areas.Area = null
  
  var watchful = 0
  var shadowy = 0
  var dangerous = 0
  var persuasive = 0
  
  var items = Map[String, Int]()
  var qualities = Map[String, Int]()
  
  var title = ""
  
  var eventIDs = Map[String, Int]()
  var branchIDs = Map[String, Int]()
  var itemIDs = Map[String, Int]()
  
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
    /*
        update_script = soup.find_all('script')[1].string
        match = re.search(r'setActionsLevel\((\d+)', update_script)
        self.actions = int(match.group(1))

        effects = soup.find('div', class_='quality_update_box').find_all('p')
        for tag in effects:
            content = ''.join(tag.strings)
            if not 'You succeeded' in content:
                print('    {0}'.format(content))

            inner_match = re.match(r'(.+) has (increased|dropped) to (\d+)', content)
            if inner_match:
                qname, qval = inner_match.group(1,3)
                self.qualities[qname] = int(qval)

            inner_match = re.match(r'An occurrence! Your \'(.+)\' quality is now (\d+)', content)
            if inner_match:
                qname, qval = inner_match.group(1,2)
                self.qualities[qname] = int(qval)

            inner_match = re.match(r'(.+) has been reset', content)
            if inner_match:
                qname = inner_match.group(1)
                self.qualities[qname] = 0

            inner_match = re.match(r'You\'ve (gained|lost) (\d+) x (.+) \(new total (\d+)\)', content)
            if inner_match:
                iname, ival = inner_match.group(3,4)
                self.items[iname] = int(ival)

            inner_match = re.match(r'You now have (\d+) x (.+)', content)
            if inner_match:
                iname, ival = inner_match.group(2,1)
                self.items[iname] = int(ival)

            inner_match = re.match(r'You no longer have any of this: \'(.+)\'', content)
            if inner_match:
                iname = inner_match.group(1)
                self.items[iname] = 0
     */
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
    def getStat(id: Int) = {
      var level = outerSoup.select("span#infoBarQLevel"+id).text
      var bonus = outerSoup.select("span#infoBarBonusPenalty"+id).text
      
      if (bonus != "")
        level.toInt + bonus.toInt
      else
        level.toInt
    }
    
    watchful = getStat(209)
    shadowy = getStat(210)
    dangerous = getStat(211)
    persuasive = getStat(212)
    
    //Non-item qualities: <strong> tags in you_bottom_lhs div
    val qualityPattern = """(.*) (\d+) .*""".r
    qualities = (for (quality <- innerSoup.select("div.you_bottom_lhs strong") if quality.text().matches(qualityPattern.toString)) yield {
      val qualityPattern(name, quantity) = quality.text()
      name -> quantity.toInt
    }).toMap.withDefaultValue(0)
    
    //Carried item qualities: the link-containing slots of you_bottom_rhs div
    val tooltipPattern = """<strong>(\d+) x (.*?)<.*""".r
    val imagePattern = """infoBarQImage(\d*)""".r
    val possessions = for (item <- innerSoup.select("div.you_bottom_rhs li > a.tooltip")) yield {
      val tooltipPattern(quantity, name) = item.attr("title") 
      val imagePattern(id) = item.select("div").last.attr("id")
      (name, id.toInt, quantity.toInt)
    }

    itemIDs = possessions.map(i => i._1 -> i._2).toMap
    items = possessions.map(i => i._1 -> i._3).toMap.withDefaultValue(0)
    
    //Name and description: at the start of the /Me page in redesign_heading div
    name = innerSoup.select("div.redesign_heading > h1 > a").first.text
    description = innerSoup.select("div.redesign_heading > p").first.text
  }
}