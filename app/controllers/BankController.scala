package controllers

import javax.inject._
import models._
import play.api.libs.json.Json
import play.api.mvc._
import services.LoadDataService

import scala.concurrent.{ExecutionContext, Future}

class BankController @Inject()(repo: BankRepository, loadDataService: LoadDataService, cc: MessagesControllerComponents)
                              (implicit ec: ExecutionContext) extends AbstractController(cc) {

  /**
   * Get bank based on bank identifier
   *
   * @param bankIdentifier Unique identifier of bank
   * @return 200 with [[Bank]] if found, 404 otherwise
   */
  def getBankByIdentifier(bankIdentifier: Long) = Action.async(parse.default) { implicit req =>
    repo.getByBankIdentifier(bankIdentifier) map {
      case Some(bank) =>
        println(bank.name)
        Ok(Json.toJson(bank))
      case None =>
        NotFound(Json.toJson("error" -> s"Bank with identifier $bankIdentifier not found."))
    }
  }

  /**
   * Endpoint to trigger a background process that inserts all the mocked data of testdata,csv into Postgres
   *
   * @return 200
   */
  def loadData = Action.async { implicit request =>
    loadDataService.loadDataIntoDb
    Future.successful(NoContent)
  }
}
