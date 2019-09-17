package models

import play.api.libs.json._

case class Bank(id: String, name: String, bankIdentifier: Long)
object Bank {
  implicit val bankFormat = Json.format[Bank]
}
