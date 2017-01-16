package mist

/**
  * Created by zhao on 1/6/17.
  */
case class UserEvent(id: String, data: String, isLast: Boolean)

case class UserSession(userEvents: Seq[UserEvent])

