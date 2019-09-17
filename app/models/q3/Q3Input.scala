package models.q3

import play.api.libs.json.{Format, Json}

case class Q3Input (`type`: String)
object Q3Input {
  implicit def jsonFormat: Format[Q3Input] = Json.format[Q3Input]
}
