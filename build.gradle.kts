import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.30"
    jacoco
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

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        csv.required.set(true)
    }
}

sonarqube {
    properties {
        property("sonar.projectKey", "JavaDream_migration")
        property("sonar.organization", "javadream")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}




