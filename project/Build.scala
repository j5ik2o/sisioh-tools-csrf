import sbt._
import sbt.Keys._

object ProjectBuild extends Build {
  val appDependencies = Seq(
    // Add your project dependencies here,
    "commons-codec" % "commons-codec" % "1.4",
    "javax.servlet" % "servlet-api" % "2.4" % "provided"
  )

  lazy val root = Project(
    id = "root",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "sisioh-tools-csrf",
      organization := "org.sisioh",
      version := "0.1-SNAPSHOT",
      scalaVersion := "2.9.1",

      libraryDependencies ++= appDependencies

    )
  )
}
