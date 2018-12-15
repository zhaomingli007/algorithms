abstract class Res[+T]{
  //Bind
  def flatMap[U](f:T=>Res[U]):Res[U]
  def map[U](f:T=>U):Res[U]
}


final case class Ok[+T](x:T) extends Res[T]{
  override def flatMap[U](f:T =>Res[U]) = f(x)
  override def map[U](f:T=>U) = Ok(f(x))
}

final case class Err[+T](err:String) extends Res[T]{
  def flatMap[U](f:T=>Res[U]):Res[U]=this.asInstanceOf[Res[U]]
  override def map[U](f:T=>U) = this.asInstanceOf[Res[U]]
}

//Identity
object Res {
  def apply[T](t: => T):Res[T]= Ok(t)
}


def validateReq(uid:String) = uid match {
  case id if( id!=null && id.length > 2) => Ok(id)
  case _ =>  Err("user id is invalid")
}

case class User(id:String,name:String,age:Int)
val dbusrs = List(User("111","john",23),User("222","alan",28))

def connectDB(uid:String)  = dbusrs.filter(_.id == uid)

def getUser(uid:String) = connectDB(uid) match {
  case usr::_ if usr!=null =>Ok(usr)
  case _ =>  Err("Failed to get user from db")
}

def updateUsrName(usr:User) = usr.name match{
  case name if name.startsWith("a") => Ok(User(usr.id,name.replaceFirst("a","A"),usr.age))
  case _ => Err("Update user name failed")
}

def sendEmail(usr:User) = Ok(usr,"Email sent successfully!")

//For
val r = validateReq("222").flatMap(getUser).flatMap(updateUsrName).flatMap(sendEmail)
r match {
  case Ok(ele) => println(ele)
  case Err(exc) =>println(exc)
}

for{
 uid <- validateReq("111")
 usr <- getUser(uid)
 upusr <- updateUsrName(usr)
 r <- sendEmail(upusr)
} yield r

