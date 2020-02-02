import sbt.Keys._
import sbt._
import xerial.sbt.Sonatype.autoImport.sonatypePublishToBundle

object ReleaseSettings {

  private lazy val library: Seq[Def.Setting[_]] = Seq(
    developers := List(Developer("$developer_id$", "$developer_name$", "$developer_email$", url("$developer_url$"))),
    homepage := Some(CommonValues.homepage),
    // TODO: decide which license to use and update LICENSE.md in the root directory
    // keep consistent with DefaultSettingsPlugin.sbtHeaderSettings
    licenses += "MIT" -> url("https://opensource.org/licenses/MIT"),
    publishMavenStyle := true,
    publishTo := sonatypePublishToBundle.value,
    scmInfo := Some(ScmInfo(CommonValues.homepage, CommonValues.connection))
  )

  // https://www.lightbend.com/blog/scala-inliner-optimizer
  private def optimized(inlineFromPackage: String): Seq[Def.Setting[_]] = {
    val options = sys.env.get("RELEASE") match {
      case Some("true") =>
        Seq("-opt:l:method", "-opt:l:inline", s"-opt-inline-from:\$inlineFromPackage.**", "-opt-warnings")
      case _ =>
        Seq.empty
    }
    scalacOptions ++= options
  }

  lazy val disabled = Seq(skip in publish := true)

  def libraryOptimized(inlineFromPackage: String): Seq[Def.Setting[_]] = library ++ optimized(inlineFromPackage)

}

