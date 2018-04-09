lazy val root = (project in file("."))
  .enablePlugins(GitBranchPrompt, GitVersioning, ParadoxPlugin)
  .settings(
    name := "$name$",

    resolvers ++= Dependencies.resolvers,
    libraryDependencies ++= Dependencies.rootLibraries,

    // trigger the licence report dump
    (mappings in Compile) in paradoxMarkdownToHtml ++= Seq(
      (dumpLicenseReport).value / "Licenses.md" -> "licenses.md"
    )

  )