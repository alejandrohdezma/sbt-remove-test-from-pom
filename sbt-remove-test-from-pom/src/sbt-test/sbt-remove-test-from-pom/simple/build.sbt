scalaVersion := "2.13.13"

libraryDependencies += "org.specs2" %% "specs2-core" % "4.9.2" % Test

//To ensure local proxies are not included
pomIncludeRepository := (_ => false)

TaskKey[Unit]("makePomInBaseDirectory") := makePom.value.renameTo(baseDirectory.value / "gen.pom")
