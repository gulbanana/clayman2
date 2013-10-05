import api._
import common._

package object hors {
  implicit lazy val me = new HardcodedCharacterProvider(settings.Username, settings.Password)
}