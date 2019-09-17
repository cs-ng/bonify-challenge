package controllers

import javax.inject._
import models.q3.{ContractEnum, Q3Input}
import play.api.libs.json.Json
import play.api.mvc._
import services.Q3Service

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

@Singleton
class Q3Controller @Inject()(q3Service: Q3Service, cc: ControllerComponents) extends AbstractController(cc) {

  def index = Action {
    Ok("Bonify Challenge - Ng Chi Siang")
  }

  /**
   * To println the value of `type` based on [[ContractEnum]]
   *
   * @return 204 if `type` is valid, 400 otherwise
   */
  def q3() = Action.async(parse.json[Q3Input]) { implicit req =>
    Try(ContractEnum.withName(req.body.`type`)) match {
      case Success(value) =>
        q3Service.processContract(value)
        Future.successful(NoContent)
      case Failure(_) => Future.successful(BadRequest(Json.obj("error" -> s"Unknown type ${req.body.`type`}")))
    }
  }

}
