import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.30"
    id("org.sonarqube") version "3.3"
}

repositories {
    mavenCentral()
}

allprojects {
    group = "info.dreamcoder"
    version = "1.0-SNAPSHOT"
}

dependencies {}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "14"
}

sonarqube {
    properties {
        property("sonar.projectKey", "JavaDream_migration")
        property("sonar.organization", "javadream")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}




