import com.typesafe.sbt.SbtStartScript

name := "clayman"

version := "2.0-SNAPSHOT"

scalaVersion := "2.10.2"
 
resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq (
  "org.scalatest" %% "scalatest" % "1.9.1" % "test", 
  "net.databinder.dispatch" %% "dispatch-core" % "0.10.1",
  "net.databinder.dispatch" %% "dispatch-tagsoup" % "0.10.1"
)

seq(SbtStartScript.startScriptForClassesSettings: _*)
