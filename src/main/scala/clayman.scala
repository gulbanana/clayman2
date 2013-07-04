import jobs._
import com.ning.http.client.AsyncHttpClientConfig

object Clayman {
  val jobs = Map(
    "login" -> login,
    "grind" -> grind,
    "court" -> court
  )
  
  def main(args: Array[String]) {
    //asynchttpclient does not support relative redirects. but, maybe someday it will.
    //dispatch.Http.configure(_ setFollowRedirects(true)) 
  
    //run selected job
    jobs(args(0))()
    
    //dispose Dispatch
    dispatch.Http.shutdown()
  }
}
