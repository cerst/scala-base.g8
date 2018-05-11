lazy val root = (project in file("."))
  .enablePlugins(GitBranchPrompt, GitVersioning, ParadoxPlugin)
  // all these settings are only relevant to the "root" project which is why they are not defined in CommonSettingsPlugin.scala
  .settings(
    name := "$name$-root",
    // trigger dump-license-report in all other projects and rename the output
    // (paradox uses and first heading as link name in '@@@index' containers AND cannot handle variables in links)
    (mappings in Compile) in paradoxMarkdownToHtml ++= Seq(
      dumpLicenseReport.value / (licenseReportTitle.value + ".md") -> "licenses/root.md",
      (core / dumpLicenseReport).value / ((core / licenseReportTitle).value + ".md") -> "licenses/core.md"
    ),
    // trigger test compilation in projects which contain snippets to be shown in the documentation
    paradox in Compile := {
      val _ = (core / compile in Test).value
      (paradox in Compile).value
    },
    // properties to be accessible from within the documentation
    paradoxProperties ++= Map(
      "version" -> version.value
    ),
    paradoxTheme := Some(builtinParadoxTheme("generic")),
    // this project is not supposed to be used externally, so don't publish
    publish := {}
  )

lazy val core = (project in file("core"))
  .enablePlugins(GitBranchPrompt, GitVersioning)
  .settings(
    name := "$name$",
    resolvers ++= Dependencies.resolvers,
    libraryDependencies ++= Dependencies.coreLibraries
  )
