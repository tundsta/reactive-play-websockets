package actors

import akka.actor.{ActorRef, Actor}

class EchoActor(out:ActorRef) extends Actor {

  def receive = {
    case s: String => out ! s
  }

}
