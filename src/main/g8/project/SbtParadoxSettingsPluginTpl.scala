import com.lightbend.paradox.sbt.ParadoxPlugin
import com.lightbend.paradox.sbt.ParadoxPlugin.autoImport.{builtinParadoxTheme, paradoxProperties, paradoxTheme}
import sbt.Keys.{name, version}
import sbt.{AutoPlugin, Def, Plugins}


/**
  * The paradox plugin must be enabled explicitly, so its settings are bundled in a dedicated project.
  *
  * @see https://developer.lightbend.com/docs/paradox/latest/
  */
object SbtParadoxSettingsPluginTpl extends AutoPlugin {

  override def requires: Plugins = ParadoxPlugin

  override def trigger = allRequirements

  override def projectSettings: Seq[Def.Setting[_]] = {
    paradoxProperties ++= Map(
      "name" -> name.value,
      "version" -> version.value
    )
    paradoxTheme := Some(builtinParadoxTheme("generic"))
  }

}
