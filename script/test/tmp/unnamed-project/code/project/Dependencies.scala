import sbt._

object Dependencies {

  private object Version {

  }

  // comment licenses for dependencies using the SPDX short identifier (see e.g. https://opensource.org/licenses/Apache-2.0)
  // rationale: double check the license when adding a new library avoid having to remove a problematic one later on when it is in use and thus hard to remove
  private object CompilerPlugin {

  }

  // comment licenses for dependencies using the SPDX short identifier (see e.g. https://opensource.org/licenses/Apache-2.0)
  // rationale: double check the license when adding a new library avoid having to remove a problematic one later on when it is in use and thus hard to remove
  // example:
  //    // Apache-2.0
  //    val AkkaStream = "com.typesafe.akka" %% "akka-stream" % Version.Akka
  private object Library {

  }

  val core: Seq[ModuleID] = {
    val compile = Seq[ModuleID]()
    val provided = Seq[ModuleID]() map (_ % Provided)
    val test = Seq[ModuleID]() map (_ % Test)
    compile ++ provided ++ test
  }

}