# Changelog

## 0.6.0
* Fix _cat_ directory in _scripts/release/run.sh_
* Update _README_ concerning plugins added in v0.4.0
* Extend build to set-up cross-publish based on Scala 2.13 

## 0.5.0
* Fix path in _include-files_ (publish -> release)

## 0.4.0
* Predefine a release using sbt-site and sbt-ghpages
* Bump various library and plugin versions
* Add .gitignore for temporary scala-base clone directory

## 0.3.0
* Add _build.sbt_ to _/project_ to control the Scala version of the build
    * required to avoid an outdated Scala version failing the task to search for plugin updates
    * breaking changes as _include-files_ has a respective new entry
* Version upgrades
    * (g8) Sbt to 1.2.3
    * (g8) sbt-giter8 to 0.11.0
    * Scala to 2.12.7
    * Sbt to 1.2.3
    * sbt-native-packager to 1.3.9
    * sbt-paradox to 0.4.2


## 0.2.0
* Add changelog
* Add sbt-api-mappings plugin
* Add sbt-native-packager settings plugin
* Change scalafmt _maxColumn_ to 120 (was 100)
* Improve README doc
* Version bumps
  * sbt-native-packager to 1.3.6
