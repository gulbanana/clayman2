import api._

package object common extends Did {
  def with_character(f: Character => Unit) = f(new Character(settings.Username, settings.Password))
  def deprioritise[T <% Ordered[T]](xs: Set[T], worst: T) = xs.toSeq.sortBy(b => -Math.abs(b.compareTo(worst))).head
}