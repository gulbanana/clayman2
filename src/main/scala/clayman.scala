import reflect.runtime.{universe=>reflect}
import com.ning.http.client.AsyncHttpClientConfig
import jobs._

object Clayman {
  def main(args: Array[String]) {  
    //locate and run a job
    val mirror = reflect.runtimeMirror(getClass().getClassLoader())
    val jobName = mirror.staticModule("jobs."+args(0))
    val job = mirror.reflectModule(jobName).instance.asInstanceOf[Job]
    job()
    
    //dispose Dispatch
    dispatch.Http.shutdown()
  }
}
