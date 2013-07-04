package london
import collection.JavaConversions._
import org.jsoup.nodes._

class Status {
  var actions = 0
  var actionCap = 10
  var description = ""
  var items = Map[String, Int]()
  var location:Areas.Area = null
  var name = ""
  var title = ""
    
  var watchful = 0
  var shadowy = 0
  var dangerous = 0
  var persuasive = 0
    
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
    val actionPattern = """(\d+)/(\d+)""".r
    val actionPattern(current, max) = outerSoup.select("span.actions_remaining").text
    actions = current.toInt
    actionCap = max.toInt

    val areaPattern = """(?s).*CurrentArea\((\d+),.*""".r
    val areaPattern(areaID) = outerSoup.select("div#currentAreaSection > script").first.data
    location = Areas(areaID.toInt)
    
    def getStat(id: Int) = {
      var level = outerSoup.select("span#infoBarQLevel"+id).text
      var bonus = outerSoup.select("span#infoBarBonusPenalty"+id).text
      
      println(bonus)
      if (bonus != "")
        level.toInt + bonus.toInt
      else
        level.toInt
    }
    
    watchful = getStat(209)
    shadowy = getStat(210)
    dangerous = getStat(211)
    persuasive = getStat(212)
    /*
    self.qualities = defaultdict(int)
    quals = inner_soup.find('div', class_='you_bottom_lhs')
    for quality in [q.string for q in quals('strong') if q.string]:
        matches = re.search(r'(.*) (\d+)', quality.string)
        if matches:
            name = matches.group(1)
            quantity = int(matches.group(2))
            self.qualities[name] = int(quantity)

    self._items_by_id = dict()
    self.items = defaultdict(int)
    equipment = inner_soup.find('div', id='inventory')
    possessions = inner_soup.find('div', class_='you_bottom_rhs')
    for item in [possession for possession in  possessions('li') if len(possession.a.contents) > 0]:
        tooltip = item.a['title']
        matches = re.search(r'>(\d+) x (.*?)<', tooltip)
        name = matches.group(2)
        quantity = int(matches.group(1))

        imagediv = item('div')[1]['id']
        match = re.search(r'infoBarQImage(\d+)', imagediv)
        id = match.group(1)

        self._items_by_id[name] = Quality(id, name, quantity)
        self.items[name] = quantity

    heading = inner_soup.find('div', class_='redesign_heading')
    self.name = heading.h1.a.string
    self.description = ' '.join(heading.p.stripped_strings)
    print('{0}: {1}.'.format(self.name, self.description))
    */
  }
}