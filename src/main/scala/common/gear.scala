package common
import london._

object gear {
  def watchful()(implicit c: Character) {
    c.equip("Luminous Neathglass Goggles")
    c.equip("Morning Suit")
    c.equip("Avid Glove")
    c.equip("Patent Scrutinizer")
    c.equip("Mirror-polished Shoes")
    c.equip("Appreciation Society")
  }
  
  def shadowy()(implicit c: Character) {
    c.equip("Sneak-Thief's Mask")
    c.equip("Anarchist's Sable")
    c.equip("Cracksman's Mittens")
    c.equip("Shepherd's Timepiece")
    c.equip("Spidersilk Slippers")
    c.equip("Devious Henchman")
  }
  
  def dangerous()(implicit c: Character) {
    c.equip("Iron Hat")
    c.equip("Gentleman's Athletic Support")
    c.equip("Spiderchitin Gauntlets")
    c.equip("Ancient Hunting Rifle")
    c.equip("Savage Hob-nailed Boots")
    c.equip("Roman (Disgraced Rattus Faber Bandit-Chief)")
  }
  
  def persuasive()(implicit c: Character) {
    c.equip("Gentleman's Hat")
    c.equip("Dignified Tailcoat")
    c.equip("Dancemaster's Dabs")
    c.equip("Brass Ring")
    c.equip("Masterwork Dancing Slippers")
    c.equip("Appreciation Society")
  }
}