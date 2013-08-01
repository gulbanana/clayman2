import london._

package object common extends Did {
  def with_character(f: Character => Unit) = f(new Character(settings.Username, settings.Password))
}