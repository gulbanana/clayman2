package hors
import common._

object opportunities extends Opportunist(Map(
  "Pass the Cat: a wriggling delivery" -> Hold
).withDefault(_ => Discard))