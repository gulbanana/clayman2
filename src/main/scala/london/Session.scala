package london
import scala.collection.mutable
import scala.collection.JavaConversions._
import dispatch._, Defaults._
import com.ning.http.client._

//synchronous cookie jar - sadly, clay men have no Future[T]
class Session {
  private val cookies = mutable.Set[Cookie]()
  
  def command(builder: RequestBuilder) {
    for(cookie <- cookies)
      builder.addCookie(cookie)
    val request = Http(builder)
    
    val response = request()
    for (cookie <- response.getCookies())
      cookies += cookie
  } 
  
  def query(builder: RequestBuilder) = {
    for(cookie <- cookies)
      builder.addCookie(cookie)
    val request = Http(builder > as.tagsoup.NodeSeq)
    
    request()
  }
}