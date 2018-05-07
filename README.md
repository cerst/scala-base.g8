# Scala Base
A [Giter8 (g8)](https://github.com/foundweekends/giter8) template for new Scala projects.  
Also serves as a source for syncing the project directory (and other shared files) of existing Scala projects.

## Usage
* Create a project using this template:
```
g8 https://github.com/cerst/scala-base.g8
```
* Add a license in _License.md_ and double check that all other license related have been setup correctly (see _licenseSettings_ in 
_project/CommonSettingsPlugin.scala).  
**Especially** ensure that the value of _headerLicense_ (i.e. the header to be displayed in source files) is suitable for your purpose.

## Development
Test your changes using:
```
sbt g8Test
```