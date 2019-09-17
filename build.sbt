name := """bonify-challenge"""
organization := "org.github.thewickedpenguin"
version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.0"

val akkaVersion = "1.1.1"
val playSlickVersion = "4.0.2"

libraryDependencies ++= Seq(
  guice,
  "org.postgresql" % "postgresql" % "42.2.1",
  "com.typesafe.play" %% "play-slick" % playSlickVersion,
  "com.typesafe.play" %% "play-slick-evolutions" % playSlickVersion,
  "com.lightbend.akka" %% "akka-stream-alpakka-csv" % akkaVersion,
  "com.lightbend.akka" %% "akka-stream-alpakka-slick" % akkaVersion,
  "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test,
  specs2 % Test
)

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-Xfatal-warnings"
)
