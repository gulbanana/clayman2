import com.typesafe.sbt.SbtStartScript

name := "clayman"

version := "2.0-SNAPSHOT"

scalaVersion := "2.10.2"
 
resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq (
  "org.scala-lang" % "scala-reflect" % "2.10.2",
  "org.scalatest" %% "scalatest" % "1.9.1" % "test", 
  "net.databinder.dispatch" %% "dispatch-jsoup" % "0.10.1",
  "ch.qos.logback" % "logback-classic" % "1.0.12"
)

seq(SbtStartScript.startScriptForClassesSettings: _*)
