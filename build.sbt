import com.typesafe.sbt.SbtStartScript

name := "clayman"

version := "2.0-SNAPSHOT"

scalaVersion := "2.11.4"
 
resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq (
  "org.scala-lang" % "scala-reflect" % "2.11.4",
  "net.databinder.dispatch" %% "dispatch-jsoup" % "0.11.2",
  "ch.qos.logback" % "logback-classic" % "1.0.12"
)

seq(SbtStartScript.startScriptForClassesSettings: _*)
