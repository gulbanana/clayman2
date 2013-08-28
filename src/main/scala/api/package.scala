import dispatch._
import com.ning.http.client._

//dispatch extensions for handling redirects explicitly - not currently used
package object api {
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