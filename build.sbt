name := "clayman"

version := "2.0-SNAPSHOT"

scalaVersion := "2.10.2"
 
resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq (
	"org.scalatest" % "scalatest_2.10" % "1.9.1" % "test"
)
