package london

import org.scalatest.FunSuite

class CharacterTest extends FunSuite {
 
  test("character exists") {
    assert(new Character() != null)
  }

}
