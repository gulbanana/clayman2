package london

class Character(username: String, password: String) {
  var actions = 0
  var actionCap = 10
  private val game = new Site()
  
  login()
  update_status()  
  
  def travel() {
    
  }
  
  private def login() {
    game.post("/Auth/EmailLogin", Map("username" -> username, "password" -> password))
    print("Entered the Neath.")
  }
  
  private def update_status() {
    
  }
}
