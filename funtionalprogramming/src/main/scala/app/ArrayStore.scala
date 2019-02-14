
object ArrayStore {

  def store(db: Array[Array[(String, String)]])
  = db.filter(_ != null)
    .map(r => r.filter(x => x != null && x._1 != null)
      .map(x => s"${x._1}=${x._2}").mkString(";"))
    .mkString("\n")


  def load(row: String) = row match {
    case null => None
    case _ if row.trim == "" => None
    case _ => Some(row.split("\n")
      .map(txt => txt.split(";")
        .filter(_.contains("=")).map(_.split("="))
        .map(x => (x(0), x(1)))))
  }


}
