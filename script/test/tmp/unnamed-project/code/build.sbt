lazy val scalaVersionValue = "2.13.8"
lazy val crossScalaVersionsValue = List("2.12.13", scalaVersionValue)


ThisBuild / organization := "com.unknown"
ThisBuild / organizationName := "Unknown Corp"
ThisBuild / resolvers ++= Seq.empty[Resolver]
ThisBuild / scalaVersion := scalaVersionValue
ThisBuild / startYear := Some(2022)


lazy val root = (project in file("."))
  .aggregate(core)
  .settings(
    // crossScalaVersions must be set to Nil on the aggregating project
    // https: //www.scala-sbt.org/1.x/docs/Cross-Build.html#Cross+building+a+project
    crossScalaVersions := Nil,
    name := "Unnamed Project-root",
    // root intentionally does not contain any code, so don't publish
    publish / skip := true
  )


lazy val core = (project in file("core"))
  .settings(
    crossScalaVersions := crossScalaVersionsValue,
    libraryDependencies ++= Dependencies.core,
    name := "Unnamed Project"
  )
