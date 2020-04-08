# SBT plugin to remove test dependencies from POM 

[![][github-action-badge]][github-action] [![][maven-badge]][maven] [![][steward-badge]][steward] 

This plugin removes test dependencies from SBT-generated POMs if the POM's project sets the `publishArtifact in Test` setting to `false` (which is the [default value](https://github.com/sbt/sbt/blob/0b12862caf63d1c23449391be6329d947b474157/main/src/main/scala/sbt/Defaults.scala#L2173)).

If a projects enables the publication of test artifacts this plugin will do nothing:

```sbt
publishArtifact in Test := true
```

## Installation

Add the following line to your `plugins.sbt` file:

```sbt
addSbtPlugin("com.alejandrohdezma" %% "sbt-remove-test-from-pom" % "0.1.0")
```

[github-action]: https://github.com/alejandrohdezma/sbt-remove-test-from-pom/actions
[github-action-badge]: https://img.shields.io/endpoint.svg?url=https%3A%2F%2Factions-badge.atrox.dev%2Falejandrohdezma%2Fsbt-remove-test-from-pom%2Fbadge%3Fref%3Dmaster&style=flat

[maven]: https://search.maven.org/search?q=g:%20com.alejandrohdezma%20AND%20a:sbt-remove-test-from-pom
[maven-badge]: https://maven-badges.herokuapp.com/maven-central/com.alejandrohdezma/sbt-remove-test-from-pom/badge.svg?kill_cache=1

[steward]: https://scala-steward.org
[steward-badge]: https://img.shields.io/badge/Scala_Steward-helping-brightgreen.svg?style=flat&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA4AAAAQCAMAAAARSr4IAAAAVFBMVEUAAACHjojlOy5NWlrKzcYRKjGFjIbp293YycuLa3pYY2LSqql4f3pCUFTgSjNodYRmcXUsPD/NTTbjRS+2jomhgnzNc223cGvZS0HaSD0XLjbaSjElhIr+AAAAAXRSTlMAQObYZgAAAHlJREFUCNdNyosOwyAIhWHAQS1Vt7a77/3fcxxdmv0xwmckutAR1nkm4ggbyEcg/wWmlGLDAA3oL50xi6fk5ffZ3E2E3QfZDCcCN2YtbEWZt+Drc6u6rlqv7Uk0LdKqqr5rk2UCRXOk0vmQKGfc94nOJyQjouF9H/wCc9gECEYfONoAAAAASUVORK5CYII=
