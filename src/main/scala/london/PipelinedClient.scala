package london
import scala.collection.mutable
import scala.collection.JavaConversions._
import dispatch._, Defaults._
import com.ning.http.client._

class PipelinedClient {
  val client = dispatch.Http
  val cookies = mutable.Set[Cookie]()
  
  //synchronous dispatch extensions - sadly, clay men have no Future[T]  
  def command(builder: RequestBuilder) {
    val request = client(builder)
    val response = request()
    for (cookie <- response.getCookies())
      cookies += cookie
  } 
  
  def query(builder: RequestBuilder) = {
    for(cookie <- cookies)
      builder.addCookie(cookie)
    val request = client(builder > as.tagsoup.NodeSeq)
    request()
  }
  
  //extra bits for handling redirects explicitly - not currently used
  implicit class RequestHandlerExtensions(builder: RequestBuilder) {
    def FOUND [T](f: Response => T) =
      ((builder.build(), new FoundFunctionHandler(f)))
  }

  class FoundFunctionHandler[T](f: Response => T) extends FunctionHandler[T](f) with FoundHandler[T]

  trait FoundHandler[T] extends AsyncHandler[T] {
    abstract override def onStatusReceived(status: HttpResponseStatus) = {
      if (status.getStatusCode / 302 == 2)
        super.onStatusReceived(status)
      else
        throw StatusCode(status.getStatusCode)
    }
  }
}