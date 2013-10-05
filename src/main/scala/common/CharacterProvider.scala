package common
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