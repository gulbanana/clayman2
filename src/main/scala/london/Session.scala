package london
import scala.collection.mutable
import scala.collection.JavaConversions._
import dispatch._, Defaults._
import com.ning.http.client._

//synchronous cookie jar - sadly, clay men have no Future[T]
class Session {
  private val cookies = mutable.Set[Cookie]()
  val client = Http.configure (_ 
    setFollowRedirects(true)
    setAllowPoolingConnection(true)
    setConnectionsPool(Http.client.getConfig().getConnectionsPool())
  )
  
  def command(builder: RequestBuilder) {
    for(cookie <- cookies)
      builder.addCookie(cookie)
      
    val request = Http(builder) //don't follow redirects here, because we need the cookie
    
    val response = request()
    for (cookie <- response.getCookies())
      cookies += cookie
  } 
  
  def query(builder: RequestBuilder) = {
    for(cookie <- cookies)
      builder.addCookie(cookie)
    val request = client(builder OK as.jsoup.Document)
    
    request()
  }
}