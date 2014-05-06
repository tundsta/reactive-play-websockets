package controllers

import play.api.mvc.{WebSocket, Action, Controller}
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.ws.WS
import play.api.Play.current
import play.api.libs.json.JsArray
import actors.{EchoActor, TwitterActor}
import akka.actor.Props
import akka.pattern.ask
import play.api.libs.concurrent.Akka
import akka.util.Timeout
import scala.concurrent.duration._

//ExecutionContext is a threadpool abstraction for scala
//under the covers based on ning - future migration to spray-http
object Application extends Controller {
  implicit val timeout = Timeout(5.seconds)
  def index = Action {
    Ok(views.html.index("Hello Play Framework"))
  }

  def echoWs = WebSocket.acceptWithActor[String, String] { request => out =>
    Props(classOf[EchoActor], out)
  }

  def foo = Action.async {
    val jwF = WS.url("http://www.jamesward.com").get()
    val twF = WS.url("http://www.twitter.com").get()
    // needs a future of a result
   for {
     jw <- jwF
     tw <- twF
   } yield Ok(tw.body   + jw.body)
  }

  def tweets(q:String) = Action.async {
    val actorRef = Akka.system.actorOf(Props[TwitterActor]) //bad practise - actor created per request without shutting down
    val f = actorRef ? q
    f map {
      case j: JsArray => Ok(j)
    }
//    val futureResponse  =
//      WS.url(s"http://localhost:9000/search/tweets?q=$q").get()
//    futureResponse map { response =>
//    Ok(JsArray((response.json \\ "text").distinct))
//    }
  }
}