import de.heikoseeberger.sbtheader.HeaderPlugin.autoImport.{HeaderLicense, headerLicense}
import sbt.Keys._
import sbt._

object CommonSettingsPlugin extends CommonSettingsPluginTpl {

  lazy val scalaVersionValue = "2.13.1"
  lazy val crossScalaVersionValues: Seq[String] = List("2.12.10", scalaVersionValue)

  // the rationale for placing settings defs here is that they should (or can) not be updated automatically using the scala-base-sync script
  // in the following, organizationName and startYear would also be required by sbt-header to generate ready-made license headers
  override lazy val projectSettings: Seq[Def.Setting[_]] = {
    tplProjectSettingsPlus(scalaVersionValue)(
      developers := List(Developer("$developer_id$", "$developer_name$", "$developer_email$", url("$developer_url$"))),
      // TODO: see https://github.com/sbt/sbt-header#configuration for setting up a license header
      headerLicense := Some(
        HeaderLicense.Custom(
          s"""TODO: define a license header
             |""".stripMargin
        )),
      homepage := Some(url("https://github.com/$developer_id$/$name$")),
      // TODO: (e.g.: "MIT" -> "https://opensource.org/licenses/MIT")
      licenses += "TODO: license type" -> url(
        "https://TODO/uri/of/project/license"
      ),
      organization := "$organization$",
      organizationName := "$organization_name$",
      resolvers ++= Dependencies.resolvers,
      scmInfo := Some(ScmInfo(homepage.value.get, "git@github.com:$developer_id$/$name$.git")),
      startYear := Some($start_year$),
      turbo := true
    )
  }

  def publishSettings(enabled: Boolean): Seq[Def.Setting[_]] = {
    if(!enabled){
      skip in publish := true
    } else {
      // refine as needed for publishing
      // publishTo := Some(if (isSnapshot.value) ??? else ???)
      Seq(
        publishMavenStyle := true
      )
    }
  }

}
