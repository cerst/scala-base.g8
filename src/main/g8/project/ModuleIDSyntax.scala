import sbt._

final class ModuleIDSyntax(moduleID: ModuleID) {

  def %(first: Configuration,
        second: Configuration,
        additional: Configuration*): ModuleID = {

    val configurations = (first +: second +: additional).mkString(",")
    moduleID.withConfigurations(Some(configurations))
  }

}

object ModuleIDSyntax {

  implicit def toModuleIDSyntax(moduleID: ModuleID): ModuleIDSyntax = {
    new ModuleIDSyntax(moduleID)
  }

}
