lazy val root = (project in file(".")).
  settings(
    name := "scala-base.g8",
    test in Test := {
      val _ = (g8Test in Test).toTask("").value
    },
    // seems like g8 does not work with Scala 2.12 - upgrading the version and runing g8Test causes java.lang.NoSuchtMethodError scala.Predef$.refArrayOps
    scalaVersion := "2.11.12",
    scriptedLaunchOpts ++= List("-Xms1024m", "-Xmx1024m", "-XX:ReservedCodeCacheSize=128m", "-XX:MaxPermSize=256m", "-Xss2m", "-Dfile.encoding=UTF-8"),
    resolvers += Resolver.url("typesafe", url("http://repo.typesafe.com/typesafe/ivy-releases/"))(Resolver.ivyStylePatterns),
    version := "0.0.1"
  )
