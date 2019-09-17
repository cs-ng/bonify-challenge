package services

import java.nio.file.Paths
import java.util.UUID

import akka.Done
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.alpakka.csv.scaladsl.CsvParsing
import akka.stream.alpakka.slick.scaladsl.{Slick, SlickSession}
import akka.stream.scaladsl.{FileIO, Sink}
import models.Bank

import scala.concurrent.Future

class LoadDataService {

  /**
   * A background fire and forget process that inserts all the data in `testdata.csv` into Postgres in a streaming manner.
   *
   * @return
   */
  def loadDataIntoDb: Future[Done] = {
    implicit val system = ActorSystem()
    implicit val ec = system.dispatcher
    implicit val materializer = ActorMaterializer()
    implicit val session = SlickSession.forConfig("slick-postgres")
    import session.profile.api._
    system.registerOnTermination(() => session.close())

    def insertBank(bank: Bank): slick.dbio.DBIO[Int] =
      sqlu"INSERT INTO bank VALUES(${bank.id}, ${bank.name}, ${bank.bankIdentifier})"

    FileIO.fromPath(Paths.get("testdata.csv"))
      .via(CsvParsing.lineScanner())
      .map(_.map(_.utf8String))
      .map(s => Bank(UUID.randomUUID().toString, s(0), s(1).toLong))
      .map(bank => {
        println(s"Inserting ${bank.name} into database")
        bank
      })
      .via(Slick.flow(parallelism = 10, insertBank))
      .runWith(Sink.ignore)
  }

}
