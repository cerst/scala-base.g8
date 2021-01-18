import sbt._

/**
  * Stores variable used in multiple places of the build
  */
object CommonValues {

  val connection = "git@github.com:$developer_id$/$name$.git"
  val homepage = url("https://github.com/$developer_id$/$name$")
  val organizationName = "$organization_name$"
  val scalaVersion = "2.13.4"
  val startYear = $start_year$

  val crossScalaVersions = List("2.12.13", scalaVersion)

}
