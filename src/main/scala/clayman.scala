import reflect.runtime.{universe=>reflect}
import com.ning.http.client.AsyncHttpClientConfig
import common.Job
import api.DisobedientException

object Clayman {
  def main(args: Array[String]) {  
    //locate and run a job
    val mirror = reflect.runtimeMirror(getClass().getClassLoader())
    val jobName = mirror.staticModule(args(0))
    val job = mirror.reflectModule(jobName).instance.asInstanceOf[Job]
    
    try {
      job.work()
    } catch {
      case de: DisobedientException => {
        println("JOB FAILED: " + de.getMessage())
      }
    }
    
    //XXX dispose Dispatch once client bugs are sorted
    //dispatch.Http.shutdown()
    System.exit(0)
  }
}
