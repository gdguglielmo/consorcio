import sbt._
import play.sbt.PlayImport.guice

object Dependencies {

  val asyncHttpClient = "org.asynchttpclient" % "async-http-client" % "2.8.1"
  val scalaTestPlay =  "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.1" % Test
  val mongoScalaDriver = "org.mongodb.scala" %% "mongo-scala-driver" % "2.6.0"
  
  val dependencies: List[ModuleID] = List(
    asyncHttpClient,
    scalaTestPlay,
    mongoScalaDriver,
    guice
  )
}
