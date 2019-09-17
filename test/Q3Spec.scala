import org.junit.runner._
import org.scalatest.concurrent.{IntegrationPatience, ScalaFutures}
import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import org.scalatestplus.play.{HtmlUnitFactory, OneBrowserPerSuite, PlaySpec}
import org.specs2.runner._
import play.api.libs.json.Json
import play.api.libs.ws.WSClient

@RunWith(classOf[JUnitRunner])
class Q3Spec extends PlaySpec with GuiceOneServerPerSuite with OneBrowserPerSuite
  with HtmlUnitFactory with ScalaFutures with IntegrationPatience {

  "App" must {
    "be runnable" in {
      go to s"http://localhost:$port"
      eventually { pageSource contains "Bonify" }
    }
  }

  "/q3" must {

    "returns 204 when post with type = dsl" in {
      implicit val ws: WSClient = app.injector.instanceOf(classOf[WSClient])
      val futureResult = wsUrl("/q3").post(Json.parse(
        """
          |{
          |	"type": "dsl"
          |}
          |""".stripMargin))
      futureResult.futureValue.status mustBe 204
    }

    "returns 204 when post with type = electricity" in {
      implicit val ws: WSClient = app.injector.instanceOf(classOf[WSClient])
      val futureResult = wsUrl("/q3").post(Json.parse(
        """
          |{
          |	"type": "electricity"
          |}
          |""".stripMargin))
      futureResult.futureValue.status mustBe 204
    }

    "returns 204 when post with type = apartment_rent" in {
      implicit val ws: WSClient = app.injector.instanceOf(classOf[WSClient])
      val futureResult = wsUrl("/q3").post(Json.parse(
        """
          |{
          |	"type": "apartment_rent"
          |}
          |""".stripMargin))
      futureResult.futureValue.status mustBe 204
    }

    "returns 400 when post with type = appartment_rent" in {
      implicit val ws: WSClient = app.injector.instanceOf(classOf[WSClient])
      val futureResult = wsUrl("/q3").post(Json.parse(
        """
          |{
          |	"type": "appartment_rent"
          |}
          |""".stripMargin))
      futureResult.futureValue.status mustBe 400
    }

    "returns 400 when post with type = abc" in {
      implicit val ws: WSClient = app.injector.instanceOf(classOf[WSClient])
      val futureResult = wsUrl("/q3").post(Json.parse(
        """
          |{
          |	"type": "abc"
          |}
          |""".stripMargin))
      futureResult.futureValue.status mustBe 400
    }

    "returns 400 when post with type = dssl" in {
      implicit val ws: WSClient = app.injector.instanceOf(classOf[WSClient])
      val futureResult = wsUrl("/q3").post(Json.parse(
        """
          |{
          |	"type": "dssl"
          |}
          |""".stripMargin))
      futureResult.futureValue.status mustBe 400
    }

    "returns 400 when post with invalid body" in {
      implicit val ws: WSClient = app.injector.instanceOf(classOf[WSClient])
      val futureResult = wsUrl("/q3").post(Json.parse(
        """
          |{
          |	"abc": "def"
          |}
          |""".stripMargin))
      futureResult.futureValue.status mustBe 400
    }

    "returns 400 when post with empty body" in {
      implicit val ws: WSClient = app.injector.instanceOf(classOf[WSClient])
      val futureResult = wsUrl("/q3").post(Json.parse("{}"))
      futureResult.futureValue.status mustBe 400
    }

  }

}
