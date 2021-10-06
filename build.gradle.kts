import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.30"
    id("org.sonarqube") version "3.3"
    id("maven-publish")
    id("jacoco")
    id("info.dreamcoder.devtools") version "1.3.2"
}

repositories {
    mavenCentral()
}

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("jacoco")
    }

    dependencies {
        implementation("info.dreamcoder:kotby:0.2")
        testImplementation("org.xerial:sqlite-jdbc:3.36.0.2")
    }

    tasks.jacocoTestReport {
        reports {
            xml.required.set(true)
            csv.required.set(true)
        }
    }

}

dependencies {
    api(project("migration-core"))
    api(project("migration-commandline"))
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


    repositories {
        mavenCentral()
        maven("https://jitpack.io")
    }

    group = "info.dreamcoder"
    version = "0.1"

    tasks.withType<KotlinCompile>() {
        kotlinOptions.jvmTarget = "16"
    }

    dependencies {
        implementation("org.junit.jupiter:junit-jupiter:5.8.1")
        testImplementation("org.amshove.kluent:kluent:1.68")
    }

    tasks.test {
        useJUnitPlatform()
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





