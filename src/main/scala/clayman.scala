import jobs._
import com.ning.http.client.AsyncHttpClientConfig

object Clayman {
  val jobs = Map(
    "grind" -> grind,
    "login" -> login
  )
  
  def main(args: Array[String]) {
    //init Dispatch
    val config = new AsyncHttpClientConfig.Builder()
    config.setFollowRedirects(true)
  
    //run selected job
    jobs(args(0))()
    
    //dispose Dispatch
    dispatch.Http.shutdown()
  }
}
