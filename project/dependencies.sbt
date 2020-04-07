// For using the plugin in its own build
unmanagedSourceDirectories in Compile +=
  baseDirectory
    .in(ThisBuild)
    .value
    .getParentFile / "sbt-remove-test-from-pom" / "src" / "main" / "scala"
