package david
import api._

object gear {
  def watchful()(implicit c: Character) {
    c.equip("Gloam-Foam")                       //+8 hat
    c.equip("Smock of Four Thousand Three Hundred and Eight Pockets") //+10 clothing
    c.equip("Insatiable Glove")                 //+8 gloves
    c.equip("A Pot of Violant Ink")             //+12 weapon
    c.equip("A Meticulously Altered Stocking")  //+3 boots
    c.equip("Scuttering Squad")                 //+8 pet
    c.equip("The Sixth City Times (Newspaper)") //+1 affiliation
    //no transport
    c.equip("Voluminous Library")               //+1 home comfort
 }
  
  def shadowy()(implicit c: Character) {
    c.equip("Sneak-Thief's Mask")               //+5 hat
    c.equip("Ratskin Suit")                     //+6 clothing
    c.equip("Lenguals")                         //+10 gloves
    c.equip("Poison-tipped Umbrella")           //+8 weapon
    c.equip("Forgotten Spidersilk Slippers")    //+9 boots
    c.equip("Scuttering Squad")                 //+8 pet
    c.equip("Gang of Hoodlums")                 //+1 affiliation
    c.equip("Ratwork Velocipede")               //+1 transport
    //no home comfort
  }
  
  def dangerous()(implicit c: Character) {
    c.equip("Iron Hat")                         //+5 hat
    c.equip("Far Khanate Lacquered Armour")     //+8 clothing
    c.equip("Spiderchitin Gauntlets")           //+5 gloves
    c.equip("Infernal Sharpshooter's Rifle")    //+10 weapon
    c.equip("Vakeskin Boots")                   //+8 boots
    c.equip("Scuttering Squad")                 //+8 pet
    //no affiliation
    //no transport
    c.equip("Formidable Basalt Gymnasium")      //+1 home comfort
  }
  
  def persuasive()(implicit c: Character) {
    c.equip("Fecund Amber Tiara")               //+10 hat
    c.equip("Parabola-Linen Suit")              //+8 clothing
    c.equip("Twelve-carat Diamond Ring")        //+8 gloves
    c.equip("A Pot of Violant Ink")             //+12 weapon
    c.equip("Masterwork Dancing Slippers")      //+5 boots
    c.equip("Blemmigan Secretary")              //+6 pet
    c.equip("A Salon")                          //+2 affiliation
    //no transport
    c.equip("Extraordinary Drinks Cabinet")     //+2 home comfort
  }
  
  def bizarre()(implicit c: Character) {
    c.equip("Fecund Amber Tiara")               //+1 hat
    c.equip("Moderately Co-operative Clothes Colony") //+1 clothing
    c.equip("Lenguals")                         //+1 gloves
    c.equip("Bejewelled Cane")                  //+1 weapon
    //no boots
    c.equip("Bifurcated Owl")                   //+2 pet
    c.equip("A Salon")                          //+1 affiliation
    c.equip("Clay Sedan Chair")                 //+2 transport
    //no home comfort
  }
  
  def dreaded()(implicit c: Character) {
    c.equip("Gloam-Foam")                       //+1 hat
    c.equip("Ratskin Suit")                     //+1 clothing
    c.equip("Iron Republic Journal")            //+1 weapon
    //no gloves
    c.equip("Kingscale Boots")                  //+1 boots
    c.equip("Bifurcated Owl")                   //+2 pet
    c.equip("Gang of Hoodlums")                 //+2 affiliation
    c.equip("Obdurate Stallion")                //+2 transport
    c.equip("Probably A Coincidence")           //+1 home comfort
  }
  
  def respectable()(implicit c: Character) {
    //no hat
    c.equip("Academic Gown")                    //+1 clothing
    //no gloves
    c.equip("Consonant Violin")                 //+1 weapon
    //no boots
    c.equip("Partisan Messenger Tortoise")      //+1 pet
    c.equip("Membership of God's Editors")      //+4 affiliation
    c.equip("Ratwork Velocipede")               //+2 transport
    //no home comfort
  }
  
  def bdr()(implicit c: Character) {
    c.equip("Gloam-Foam")                       //+1 hat
    c.equip("Academic Gown")                    //+1 clothing
    c.equip("Lenguals")                         //+1 gloves
    c.equip("Iron Republic Journal")            //+2 weapon
    c.equip("Kingscale Boots")                  //+1 boots
    c.equip("Bifurcated Owl")                   //+4 pet
    c.equip("Membership of God's Editors")      //+4 affiliation
    c.equip("Ratwork Velocipede")               //+4 transport
    c.equip("Probably A Coincidence")           //+1 home comfort
  }
  
  def balanced()(implicit c: Character) {
    c.equip("Fecund Amber Tiara")               //+10 persuasive
    c.equip("Far Khanate Lacquered Armour")     //+8 dangerous, +4 persuasive
    c.equip("Insatiable Glove")                 //+8 watchful, -1 shadowy, +2 dangerous, -1 persuasive
    c.equip("A Pot of Violant Ink")             //+12 watchful, +12 persuasive, +1 bizarre
    c.equip("Kingscale Boots")                  //+8 shadowy, +6 dangerous
    c.equip("Bifurcated Owl")                   //+5 watchful, +5 shadowy, +5 dangerous, +5 persuasive
    c.equip("The Sixth City Times (Newspaper)") //+1 watchful, +1 persuasive
    c.equip("Ratwork Velocipede")               //+1 shadowy
    c.equip("Formidable Basalt Gymnasium")      //+1 dangerous
  }
}
