import london._

package object common extends Did {
  def with_character(f: Character => Unit) = f(new Character(settings.Username, settings.Password))
  
  def repeat(f: => Unit)(implicit c: Character) {
    val deck = opportunities.london
    deck.mill()
    
    val buffer = c.actionCap - 4
    while (c.actions > buffer) {
      if (deck.act()) {
        deck.mill()
      } else {
        f
      }
    }
  }
}