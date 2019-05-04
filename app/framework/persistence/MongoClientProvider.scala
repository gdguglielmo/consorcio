package framework.persistence

import java.util.concurrent.TimeUnit

import javax.inject.Inject
import com.gguglielmo.consorcio.model.Thing
import com.mongodb.Block
import org.mongodb.scala.{MongoClient, MongoClientSettings, ServerAddress, connection}
import play.api.Configuration
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.mongodb.scala.connection.{ClusterSettings, SocketSettings}

import scala.collection.JavaConverters._

class MongoClientProvider @Inject()(config: Configuration) {

  val socketSettings: Block[SocketSettings.Builder] = {
    b =>
      val settings = SocketSettings.builder().connectTimeout(1000, TimeUnit.MILLISECONDS).readTimeout(200, TimeUnit.MILLISECONDS).build()
      b.applySettings(settings)
  }

  val clusterSettings: Block[ClusterSettings.Builder] = {
    b => b.hosts(config.get[Seq[String]]("mongodb.hosts").map(new ServerAddress(_)).asJava)
  }

  val settings = MongoClientSettings.builder().
    applyToSocketSettings(socketSettings).
    applyToClusterSettings(clusterSettings).build()

  val mongoClient = MongoClient(settings)

  val codecRegistry = fromRegistries(fromProviders(classOf[Thing]), DEFAULT_CODEC_REGISTRY)

  val database = mongoClient.getDatabase(config.get[String]("mongodb.database-name")).withCodecRegistry(codecRegistry)

}
