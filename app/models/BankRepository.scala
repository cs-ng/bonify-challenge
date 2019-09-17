package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

/**
 * A repository for [[Bank]].
 *
 * @param dbConfigProvider The Play db config provider.
 */
@Singleton
class BankRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  import dbConfig._
  import profile.api._

  private class BankTable(tag: Tag) extends Table[Bank](tag, "bank") {
    def id = column[String]("id")
    def name = column[String]("name")
    def bankIdentifier = column[Long]("bank_identifier")

    def * = (id, name, bankIdentifier) <> ((Bank.apply _).tupled, Bank.unapply)
  }

  private val banks = TableQuery[BankTable]

  def getByBankIdentifier(bankIdentifier: Long): Future[Option[Bank]] = db.run {
    banks.filter(_.bankIdentifier === bankIdentifier).result.headOption
  }

}
