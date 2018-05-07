import java.net.URL

import com.typesafe.sbt.GitPlugin.autoImport.git
import sbt.Def
import sbt.Keys.{licenses, organization, organizationName, startYear}

object CommonSettingsPlugin extends CommonSettingsPluginTpl {

  // in the following, licenses, organizationName and startYear are required by sbt-header
  override lazy val projectSettings: Seq[Def.Setting[_]] = tplProjectSettingsPlus(
    git.baseVersion := "0.0.0",
    licenses += ("MIT", new URL("https://opensource.org/licenses/MIT")),
    organization := "$organization$",
    organizationName := "$organization_name$",
    startYear := Some(2018)
  )
}
