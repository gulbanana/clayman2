import london._

package object common {
  def with_character(f: Character => Unit) = f(new Character(settings.Username, settings.Password))
  
  def repeat(f: => Unit)(implicit c: Character) {
    opportunities.mill()
    
    val buffer = c.actionCap - 4
    while (c.actions > buffer)
      if (opportunities.can_act())
        opportunities.act_once()
      else
        f
  }
}