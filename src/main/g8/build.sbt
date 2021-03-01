lazy val root = (project in file("."))
  .aggregate(core)
  .settings(
    // root intentionally does not contain any code, so don't publish
    ReleaseSettings.disabled,
    // crossScalaVersions must be set to Nil on the aggregating project
    // https: //www.scala-sbt.org/1.x/docs/Cross-Build.html#Cross+building+a+project
    crossScalaVersions := Nil,
    name := "$name$-root"
  )

lazy val core = (project in file("core"))
  .settings(
    // TODO: decide whether or not this is to be published
    ReleaseSettings.libraryOptimized("$package$"),
    crossScalaVersions := SharedValues.crossScalaVersions,
    libraryDependencies ++= Dependencies.core,
    name := "$name$"
  )
