import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotestVersion = "4.6.1"

plugins {
    kotlin("jvm") version "1.5.30"
    jacoco
    id("org.sonarqube") version "3.3"
}

group = "info.dreamcoder"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.vertical-blank:sql-formatter:2.0.2")

    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")


}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "13"
}

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
