import london._

package object common extends Did {
  def with_character(f: Character => Unit) = f(new Character(settings.Username, settings.Password))
  
  def repeat[T](c: Character, f: Character => T) {
    val buffer = c.actionCap - 4
    while (c.actions > buffer)
      f(c)
  }
}