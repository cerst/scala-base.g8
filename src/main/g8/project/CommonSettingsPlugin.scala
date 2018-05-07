import java.net.URL

import com.typesafe.sbt.GitPlugin.autoImport.git
import de.heikoseeberger.sbtheader.HeaderPlugin.autoImport.{HeaderLicense, headerLicense}
import sbt.Def
import sbt.Keys.{licenses, organization, organizationName, startYear}

object CommonSettingsPlugin extends CommonSettingsPluginTpl {

  // in the following, organizationName and startYear would also be required by sbt-header to generate ready-made license headers
  override lazy val projectSettings: Seq[Def.Setting[_]] = tplProjectSettingsPlus(
    git.baseVersion := "0.0.0",

    headerLicense := Some(HeaderLicense.Custom(
      s"""Copyright (c) \${startYear.value.get} \${organizationName.value}
         |All Rights Reserved
         |This file is subject to the terms and conditions defined in
         |file 'LICENSE.md', which is part of this source code package.
         |""".stripMargin
    )),

    licenses += ("$license_type$", new URL("$license_uri$")),

    organization := "$organization$",

    organizationName := "$organization_name$",

    startYear := Some($start_year$)
  )
}
