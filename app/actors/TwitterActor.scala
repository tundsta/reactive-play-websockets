package actors

import akka.actor.Actor
import play.api.libs.ws.WS
import play.api.libs.json.JsArray
import akka.pattern.pipe
import play.api.Play.current
import scala.concurrent.ExecutionContext.Implicits.global

class TwitterActor extends Actor {

  def receive = {
    case q: String =>
      val futureResponse = WS.url(s"http://localhost:9000/search/tweets?q=$q").get()
      futureResponse map { response =>
          JsArray((response.json \\ "text").distinct)
      } pipeTo sender //response is on different thread so sender ref could be different - pipeTo ensures future is piped to original sender in a threadsafe way
  }
}
