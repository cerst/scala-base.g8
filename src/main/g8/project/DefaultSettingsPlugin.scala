import com.typesafe.sbt.SbtLicenseReport.autoImport._
import de.heikoseeberger.sbtheader.HeaderPlugin.autoImport.{HeaderLicense, headerLicense}
import sbt.Keys._
import sbt._

object DefaultSettingsPlugin extends AutoPlugin {

  override def trigger = allRequirements

  // the rationale for placing settings defs here is that they should (or can) not be updated automatically using the scala-base-sync script
  // in the following, organizationName and startYear would also be required by sbt-header to generate ready-made license headers
  override lazy val projectSettings: Seq[Def.Setting[_]] = {
    sbtHeaderSettings ++
      sbtLicenseReportSettings ++
      versionToFileTaskSettings ++
      Seq(
        organization := "$organization$",
        organizationName := CommonValues.organizationName,
        resolvers ++= Dependencies.resolvers,
        scalaVersion := CommonValues.scalaVersion,
        startYear := Some(CommonValues.startYear)
      )
  }

  def sbtHeaderSettings: Seq[Def.Setting[_]] = Seq(
    // keep consistent with ReleaseSettings.licenses
    headerLicense := Some(HeaderLicense.MIT(CommonValues.startYear.toString, CommonValues.organizationName))
  )

  def sbtLicenseReportSettings: Seq[Def.Setting[_]] = Seq(
    // The ivy configurations we'd like to grab licenses for.
    licenseConfigurations := Set(Compile, Provided).map(_.name),
    licenseReportStyleRules := Some("table, th, td {border: 1px solid black;}"),
    licenseReportTitle := normalizedName.value,
    licenseReportTypes := Seq(MarkDown)
  )

  lazy val versionToFile = taskKey[Unit]("Print the version into /target/version-to-file/version")

  def versionToFileTaskSettings: Seq[Def.Setting[_]] = Seq(
    // used to read the version during release ("sbt version" causes much noise which makes extraction error-prone)
    versionToFile := {
      val file = target.value / "version-to-file" / "version"
      IO.write(file, version.value)
    }
  )

}
