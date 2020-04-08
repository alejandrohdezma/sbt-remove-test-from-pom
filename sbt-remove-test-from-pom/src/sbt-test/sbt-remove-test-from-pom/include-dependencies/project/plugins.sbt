sys.props.get("plugin.version") match {
  case Some(x) => addSbtPlugin("com.alejandrohdezma" % "sbt-remove-test-from-pom" % x)
  case _       => sys.error("https://www.scala-sbt.org/1.x/docs/Testing-sbt-plugins.html")
}