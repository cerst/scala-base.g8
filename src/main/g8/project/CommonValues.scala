import sbt._

/**
  * Stores variable used in multiple places of the build
  */
object SharedValues {

  val homepage = url("https://github.com/$developer_id$/$name$")
  val organizationName = "$organization_name$"
  val scalaVersion = "2.13.5"
  val startYear = $start_year$

  val crossScalaVersions = List("2.12.13", scalaVersion)

}
