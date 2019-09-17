import org.junit.runner._
import org.scalatest.concurrent.{IntegrationPatience, ScalaFutures}
import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import org.scalatestplus.play.{HtmlUnitFactory, OneBrowserPerSuite, PlaySpec}
import org.specs2.runner._
import play.api.libs.json.Json
import play.api.libs.ws.WSClient

@RunWith(classOf[JUnitRunner])
class BankSpec extends PlaySpec with GuiceOneServerPerSuite with OneBrowserPerSuite with HtmlUnitFactory
  with ScalaFutures with IntegrationPatience {

  "/bank/:bankIdentifier" must {

    val postbankId = "10010010"
    val eurocityId = "10030700"
    val commerzbankId = "10040000"
    val raiffeisenbankId = "22163114"

    s"return Postbank when GET with $postbankId" in {
      implicit val ws: WSClient = app.injector.instanceOf(classOf[WSClient])
      val futureResult = wsUrl(s"/bank/$postbankId").get()

      futureResult.futureValue.status mustBe 200
      (Json.parse(futureResult.futureValue.body) \ "name").as[String] mustBe "Postbank"
    }

    s"return Eurocity when GET with $eurocityId" in {
      implicit val ws: WSClient = app.injector.instanceOf(classOf[WSClient])
      val futureResult = wsUrl(s"/bank/$eurocityId").get()

      futureResult.futureValue.status mustBe 200
      (Json.parse(futureResult.futureValue.body) \ "name").as[String] mustBe "Eurocity"
    }

    s"return Commerzbank when GET with $commerzbankId" in {
      implicit val ws: WSClient = app.injector.instanceOf(classOf[WSClient])
      val futureResult = wsUrl(s"/bank/$commerzbankId").get()

      futureResult.futureValue.status mustBe 200
      (Json.parse(futureResult.futureValue.body) \ "name").as[String] mustBe "Commerzbank"
    }

    s"return Raiffeisenbank when GET with $raiffeisenbankId" in {
      implicit val ws: WSClient = app.injector.instanceOf(classOf[WSClient])
      val futureResult = wsUrl(s"/bank/$raiffeisenbankId").get()

      futureResult.futureValue.status mustBe 200
      (Json.parse(futureResult.futureValue.body) \ "name").as[String] mustBe "Raiffeisenbank"
    }

  }

}
