package london

class Site {
  private val BaseURI = new java.net.URI("http://fallenlondon.storynexus.com")
  
  def get(path: String, query: Map[String, String]) = ???
  def post(path: String, query: Map[String, String]) = ???
}