import common._

package object david {
  implicit lazy val me = new HardcodedCharacterProvider(settings.Username, settings.Password)
}