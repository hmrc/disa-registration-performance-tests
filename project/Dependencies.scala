import sbt.*

object Dependencies {

  val test = Seq(
    "uk.gov.hmrc"       %% "performance-test-runner" % "6.3.0"  % Test,
    "com.typesafe.play" %% "play-json"               % "2.10.8" % Test,
    "com.typesafe.play" %% "play-ahc-ws-standalone"  % "2.2.17" % Test,
    "com.typesafe.play" %% "play-ws-standalone-json" % "2.2.17" % Test,
    "org.scalatest"     %% "scalatest"               % "3.2.20" % Test
  )

}
