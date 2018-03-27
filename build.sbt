import com.typesafe.sbt.packager.Keys.dist

name := """docker-client"""
organization := "sombrainc"
version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, com.typesafe.sbt.packager.docker.DockerPlugin
  , sbtdocker.DockerPlugin).settings(dockerClientSettings)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "ch.qos.logback" % "logback-classic" % "1.1.2" exclude ("org.slf4j", "slf4j-log4j12"),
  "org.slf4j" % "slf4j-api" % "1.7.10",
  "org.apache.httpcomponents" % "httpclient" % "4.1",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.4",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.8.4",
  "com.fasterxml.jackson.core" % "jackson-annotations" % "2.8.4",
  "org.apache.commons" % "commons-lang3" % "3.4",
  "org.mongodb" % "mongodb-driver-core" % "3.4.2",
  "org.mongodb" % "mongo-java-driver" % "3.4.2",
  "org.mongodb.morphia" % "morphia" % "1.1.1")

lazy val dockerClientSettings = Seq(
  docker <<= docker dependsOn dist,
  dockerfile in docker := {
    val artifact = dist.value
    new sbtdocker.mutable.Dockerfile {
      from("java")
      env("HOME", "/root")
      run("apt-get", "update")
      run("apt-get", "install", "-y", "nmap", "unzip")
      copy(artifact, "/root/" + dist.value.getName)
      workDir("/root")
      run("unzip", "-o", dist.value.getName)
      run("chmod", "777", "-R", "/root/" + dist.value.getName.replace(".zip", ""))
      workDir("/root/" + dist.value.getName.replace(".zip", ""))
    }
  },
  imageNames in docker := Seq(
    ImageName(s"${organization.value}/${name.value}:${version.value}")
  )
)


