scalaVersion := "2.12.12"

libraryDependencies += "org.specs2" %% "specs2-core" % "4.9.2" % Test

//To ensure local proxies are not included
pomIncludeRepository := (_ => false)

publishArtifact in Test := true

TaskKey[Unit]("makePomInBaseDirectory") := makePom.value.renameTo(baseDirectory.value / "gen.pom")
