ThisBuild / scalaVersion := "2.12.10"
ThisBuild / organization := "com.alejandrohdezma"

Global / onChangedBuildSource := ReloadOnSourceChanges

addCommandAlias("ci-test", "fix --check; mdoc; scripted")
addCommandAlias("ci-docs", "mdoc; headerCreateAll")

skip in publish := true

lazy val docs = project
  .in(file("sbt-remove-test-from-pom-docs"))
  .enablePlugins(MdocPlugin)
  .settings(skip in publish := true)
  .settings(mdocOut := file("."))

lazy val `sbt-remove-test-from-pom` = project
  .enablePlugins(SbtPlugin)
  .settings(scriptedLaunchOpts += s"-Dplugin.version=${version.value}")
