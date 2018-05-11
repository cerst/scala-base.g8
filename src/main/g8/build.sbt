lazy val root = (project in file("."))
  .enablePlugins(GitBranchPrompt, GitVersioning, ParadoxPlugin)
  .settings(

    name := "$name$-root",

    paradoxProperties ++= Map(
      "version" -> version.value
    ),

    (mappings in Compile) in paradoxMarkdownToHtml ++= Seq(
      dumpLicenseReport.value / (licenseReportTitle.value + ".md") -> "licenses/root.md",
      (core / dumpLicenseReport).value / ((core / licenseReportTitle).value + ".md") -> "licenses/core.md"
    ),

    paradoxTheme := Some(builtinParadoxTheme("generic")),

    publish := {}

  )

lazy val core = (project in file("core"))
  .enablePlugins(GitBranchPrompt, GitVersioning)
  .settings(
    name := "$name$",

    resolvers ++= Dependencies.resolvers,

    libraryDependencies ++= Dependencies.coreLibraries
  )