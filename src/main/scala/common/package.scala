import london._

package object common {
  def with_character(f: Character => Unit) {
    val c = new Character(settings.Username, settings.Password)
    f(c)
    c.logout()
  }
  
  def with_buffer(f: Character => Unit)(implicit c: Character) {
    val buffer = c.actionCap - 4
    while (c.actions > buffer)
      f(c)
  }
}