import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.30"
    id("org.sonarqube") version "3.3"
    id("maven-publish")
}

repositories {
    mavenCentral()
}

subprojects {

    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "jacoco")

    dependencies {
        implementation("info.dreamcoder:kotby:deeeb637d2")
        testImplementation("org.xerial:sqlite-jdbc:3.36.0.2")
        testImplementation("io.kotest:kotest-runner-junit5:4.6.3")
        testImplementation("io.kotest:kotest-assertions-core:4.6.3")
        testImplementation("io.kotest:kotest-property:4.6.3")
    }
}

dependencies {
    api(project("migration-core"))
    api(project("migration-commandline"))
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "16"
}

sonarqube {
    properties {
        property("sonar.projectKey", "JavaDream_migration")
        property("sonar.organization", "javadream")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

allprojects {
    apply(plugin = "maven-publish")
    apply(plugin = "java")

    repositories {
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }

    group = "info.dreamcoder"
    version = "0.1"

    dependencies {
        implementation("org.junit.jupiter:junit-jupiter:5.7.0")
    }

    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = project.group.toString()
                artifactId = project.name
                version = project.version.toString()

                from(components["kotlin"])
            }
        }

        repositories {
            mavenLocal()
        }

    }
}





