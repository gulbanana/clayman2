package common
import language.implicitConversions

trait Did {
  def did(c: Boolean)(f: => Any) = {if (c) f; c}
  
  class ConditionalAction(c: () => Boolean, f: () => Any) {
    lazy val test = c()
    lazy val act = f()
  }
  
  implicit class Conditionable(b: => Boolean) {
    def apply(f: => Any) = new ConditionalAction(() => b, () => f)
  }
  
  implicit class Shortcircuitable(b: Boolean) {
    def or(c: ConditionalAction) = b || did(c.test)(c.act)
    def or(f: => Any) = b || {f; true}
  }
}