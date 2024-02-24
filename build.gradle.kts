import java.net.URI

plugins {
  id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
  maven { url = URI("https://jitpack.io") }
  mavenCentral()
}

dependencies {
  implementation("com.sparkjava:spark-core:2.9.4")
  implementation("org.slf4j:slf4j-simple:2.0.9")
  implementation("com.sparkjava:spark-template-thymeleaf:2.7.1")
  implementation("com.google.code.gson:gson:2.10.1")
  testImplementation(platform("org.junit:junit-bom:5.10.1"))
  testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
  useJUnitPlatform()
}