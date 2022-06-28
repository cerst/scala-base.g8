lazy val scalaVersionValue = "2.13.8"
lazy val crossScalaVersionsValue = List("2.12.13", scalaVersionValue)


ThisBuild / organization := "$organization$"
ThisBuild / organizationName := "$organization_name$"
ThisBuild / resolvers ++= Seq.empty[Resolver]
ThisBuild / scalaVersion := scalaVersionValue
ThisBuild / startYear := Some($start_year$)


lazy val root = (project in file("."))
  .aggregate(core)
  .settings(
    // crossScalaVersions must be set to Nil on the aggregating project
    // https: //www.scala-sbt.org/1.x/docs/Cross-Build.html#Cross+building+a+project
    crossScalaVersions := Nil,
    name := "$name$-root",
    // root intentionally does not contain any code, so don't publish
    publish / skip := true
  )


lazy val core = (project in file("core"))
  .settings(
    crossScalaVersions := crossScalaVersionsValue,
    libraryDependencies ++= Dependencies.core,
    name := "$name$"
  )
