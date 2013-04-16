scalaVersion := "2.10.0"


scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

traceLevel := -1

logLevel := Level.Info

// disable printing timing information, but still print [success]
showTiming := false

// disable printing a message indicating the success or failure of running a task
showSuccess := false

offline := true

libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.10.0"

libraryDependencies += "org.scala-lang" % "scala-compiler" % "2.10.0"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1"

