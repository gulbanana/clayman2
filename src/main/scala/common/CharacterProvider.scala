package common
import java.util.Properties
import java.nio.file._
import scala.language.implicitConversions
import api.Character

trait CharacterProvider {
  def get: Character
}

object CharacterProvider {
  implicit def providerAsCharacter(p: CharacterProvider) = p.get
}

class HardcodedCharacterProvider(username: String, password: String) extends CharacterProvider {
  lazy val get = new Character(username, password)
}

class PropsCharacterProvider(key: String) extends CharacterProvider {
  val props = new Properties
  props.load(getClass().getResourceAsStream("/config.properties"))
  
  val user = props.getProperty(key + ".username")
  val pass = props.getProperty(key + ".password")
  
  lazy val get = new Character(user, pass)
}