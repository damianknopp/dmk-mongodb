package dmk.mongodb.model

case class SystemEvent(id: String, eventType: String,
    sysEvent: String, deleted: Boolean) {

  def this() = {
      this("", "", "", false)
  }

}