lazy val root = (project in file("."))
  .enablePlugins(GitBranchPrompt, GitVersioning, ParadoxPlugin)
  .settings(
    name := "$name$-root",

    // trigger the licence report dump
    (mappings in Compile) in paradoxMarkdownToHtml ++= Seq(
      (dumpLicenseReport).value / "Licenses.md" -> "licenses.md"
    )

  )

lazy val core = (project in file("core"))
  .enablePlugins(GitBranchPrompt, GitVersioning)
  .settings(
    name := "$name$",

    resolvers ++= Dependencies.resolvers,

    libraryDependencies ++= Dependencies.coreLibraries
  )