name := "M1-tests"

version := "1.0"

scalaVersion := "2.12.0"


libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0";
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test";
libraryDependencies+="org.seleniumhq.selenium" % "selenium-java" % "2.45.0" % "test";
libraryDependencies += "org.pegdown"    %  "pegdown"     % "1.6.0"  % "test";

testOptions in Test ++= Seq(Tests.Argument(TestFrameworks.ScalaTest, "-o"), Tests.Argument(TestFrameworks.ScalaTest, "-h", "reports"))
