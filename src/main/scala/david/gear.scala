package david
import london._

object gear {
  def watchful()(implicit c: Character) {
    c.equip("Luminous Neathglass Goggles")
    c.equip("Morning Suit")
    c.equip("Insatiable Glove")
    c.equip("Patent Scrutinizer")
    c.equip("Mirror-polished Shoes")
    c.equip("Appreciation Society")
  }
  
  def shadowy()(implicit c: Character) {
    c.equip("Sneak-Thief's Mask")
    c.equip("Ratskin Suit")
    c.equip("Cracksman's Mittens")
    c.equip("Kifers")
    c.equip("Spidersilk Slippers")
    c.equip("Devious Henchman")
  }
  
  def dangerous()(implicit c: Character) {
    c.equip("Iron Hat")
    c.equip("Gentleman's Athletic Support")
    c.equip("Spiderchitin Gauntlets")
    c.equip("Ancient Hunting Rifle")
    c.equip("Savage Hob-nailed Boots")
    c.equip("Rattus Faber Bandit-Chief")
  }
  
  def persuasive()(implicit c: Character) {
    c.equip("Gentleman's Hat")
    c.equip("Dignified Tailcoat")
    c.equip("Dancemaster's Dabs")
    c.equip("Brass Ring")
    c.equip("Masterwork Dancing Slippers")
    c.equip("Appreciation Society")
  }
  
  def bizarre()(implicit c: Character) {
    c.equip("Bejewelled Cane")
  }
  
  def dreaded()(implicit c: Character) {
    c.equip("Anarchist's Sable")
  }
  
  def respectable()(implicit c: Character) {
    c.equip("Morning Suit")
  }
}
