package london
import scala.collection.mutable
import scala.collection.JavaConversions._
import java.io._
import java.nio.file._
import dispatch._, Defaults._
import com.ning.http.client._

case class SerializableCookie(domain: String, name: String, value: String, path: String, maxAge: Int, secure: Boolean)

//synchronous - sadly, clay men have no Future[T]
class Session(cookieTin: String) {
  private val client = Http.configure (_ 
    setUserAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:21.0) Gecko/20100101 Firefox/21.0")
    setFollowRedirects(true)
    setAllowPoolingConnection(true)
    setConnectionsPool(Http.client.getConfig().getConnectionsPool())
  )
  
  private val tin = Paths.get("%s.tin".format(cookieTin))
  private val cookies = loadCookies().getOrElse(mutable.Set[Cookie]())
  
  private def saveCookies(cs: Iterable[Cookie]) {
    Files.deleteIfExists(tin)
    
    val writer = new ObjectOutputStream(new FileOutputStream(tin.toFile()))
    writer.writeObject(cookies.map { c => 
      SerializableCookie(c.getDomain(), c.getName(), c.getValue(), c.getPath(), c.getMaxAge(), c.isSecure())
    })
    writer.close()
  }
  
  private def loadCookies() = if (Files.exists(tin)) {
    val reader = new ObjectInputStream(new FileInputStream(tin.toFile()))
    val jar = reader.readObject().asInstanceOf[mutable.Set[SerializableCookie]].map{ c =>
      new Cookie(c.domain, c.name, c.value, c.path, c.maxAge, c.secure)
    }
    reader.close()
    Some(jar)
  } else {
    None
  }
  
  def command(builder: RequestBuilder) {
    val request = Http(builder) //don't follow redirects here, because we need the cookie    
    val response = request()
    
    cookies.clear()
    for (cookie <- response.getCookies()) 
      cookies += cookie
      
    saveCookies(cookies)    
  } 
  
  def query(builder: RequestBuilder) = {
    for(cookie <- cookies)
      builder.addCookie(cookie)
    val request = client(builder OK as.jsoup.Document)
    
    request()
  }
}