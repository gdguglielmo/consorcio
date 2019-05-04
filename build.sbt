import Dependencies._

ThisBuild / organization := "org.gguglielmo"
ThisBuild / scalaVersion := "2.12.8"

ThisBuild / version := "0.0.1-SNAPSHOT"

lazy val consorcio = (project in file(".")).enablePlugins(PlayScala).
  settings(name := "consorcio",
    libraryDependencies ++= dependencies )




// Adds additional packages into Twirl
//TwirlKeys.templateImports += "org.gguglielmo.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "org.gguglielmo.binders._"
