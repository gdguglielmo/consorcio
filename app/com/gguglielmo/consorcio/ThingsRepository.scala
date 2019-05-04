package com.gguglielmo.consorcio

import com.gguglielmo.consorcio.model.Thing
import framework.persistence.{MongoClientProvider, MongoRepository}
import javax.inject.Inject
import org.mongodb.scala.model.Filters
import play.api.Configuration

import scala.concurrent.Future

class ThingsRepository @Inject()(config: Configuration,
                                 val mongoClientProvider: MongoClientProvider) extends MongoRepository[Thing] {


  def collectionName: String = config.get[String]("mongodb.things-collection-name")

  def findByAge(age: Int): Future[Seq[Thing]] = {
    find(Filters.eq("age", age))
  }
}
