import sbt._

final class ModuleIDSyntax(moduleID: ModuleID) {

  def %(first: Configuration, additional: Configuration*): ModuleID = {
    val configurations = (first +: additional).mkString(",")
    moduleID.withConfigurations(Some(configurations))
  }

}

object ModuleIDSyntax {

  implicit def toModuleIDSyntax(moduleID: ModuleID): ModuleIDSyntax = {
    new ModuleIDSyntax(moduleID)
  }

}
