import london._

package object common extends Did {
  def with_character(f: Character => Unit) = f(new Character(settings.Username, settings.Password))
  
  def repeat[T](implicit c: Character, deck: Opportunist, f: Character => T) {
    deck.mill()
    
    val buffer = c.actionCap - 4
    while (c.actions > buffer) {
      if (deck.act()) {
        deck.mill()
      } else {
        f(c)
      }
    }
  }
}