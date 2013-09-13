package david
import api._

object gear {
  def watchful()(implicit c: Character) {
    c.equip("Luminous Neathglass Goggles")
    c.equip("Academic Gown")
    c.equip("Insatiable Glove")
    c.equip("Dilmun Club Lapel Badge")
    c.equip("Mirror-polished Shoes")
    c.equip("Appreciation Society")
    c.equip("The Sixth City Times (Newspaper)")
    c.equip("Voluminous Library")
  }
  
  def shadowy()(implicit c: Character) {
    c.equip("Sneak-Thief's Mask")
    c.equip("Ratskin Suit")
    c.equip("Cracksman's Mittens")
    c.equip("Kifers")
    c.equip("Kingscale Boots")
    c.equip("Devious Raven Advisor")
  }
  
  def dangerous()(implicit c: Character) {
    c.equip("Iron Hat")
    c.equip("Gentleman's Athletic Support")
    c.equip("Spiderchitin Gauntlets")
    c.equip("Ravenglass Knife")
    c.equip("Kingscale Boots")
    c.equip("Rattus Faber Bandit-Chief")
  }
  
  def persuasive()(implicit c: Character) {
    c.equip("Fecund Amber Tiara")
    c.equip("Dignified Tailcoat")
    c.equip("Dancemaster's Dabs")
    c.equip("Irresistible Drum")
    c.equip("Masterwork Dancing Slippers")
    c.equip("Appreciation Society")
    c.equip("A Salon")
  }
  
  def bizarre()(implicit c: Character) {
    c.equip("Fecund Amber Tiara")
    c.equip("Bejewelled Cane")
    c.equip("A Salon")
  }
  
  def dreaded()(implicit c: Character) {
    c.equip("Ratskin Suit")
    c.equip("Kingscale Boots")
    c.equip("Obdurate Stallion")
  }
  
  def respectable()(implicit c: Character) {
    c.equip("Academic Gown")
    c.equip("Appreciation Society")
    c.equip("Membership of God's Editors")
  }
  
  def bdr()(implicit c: Character) {
    c.equip("Fecund Amber Tiara")
    c.equip("Academic Gown")
    c.equip("Bejewelled Cane")
    c.equip("Kingscale Boots")
    c.equip("Appreciation Society")
    c.equip("Membership of God's Editors")
    c.equip("Obdurate Stallion")
  }
}
