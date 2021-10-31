plugins {
    kotlin("jvm") version "1.5.30"
    id("org.sonarqube") version "3.3"
    id("jacoco")
    id("info.dreamcoder.devtools") version "1.3.8"
    id("maven-publish")

    id("java-gradle-plugin")
    id("com.gradle.plugin-publish") version "0.16.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(gradleApi())

    implementation("info.dreamcoder:kotby:0.4")
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
    implementation("org.xerial:sqlite-jdbc:3.36.0.2")

    testImplementation("org.amshove.kluent:kluent:1.68")
    testImplementation("io.mockk:mockk:1.12.0")
    testImplementation("mysql:mysql-connector-java:8.0.26")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.5.30")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        csv.required.set(true)
    }
}

tasks.test {
    useJUnitPlatform()
}

gradlePlugin {
    plugins {
        create("migrationPlugin") {
            id = "info.dreamcoder.migration"
            implementationClass = "gradle.Migration"
        }
    }
}

pluginBundle {
    website = "https://github.com/JavaDream/migration"
    vcsUrl = "https://github.com/JavaDream/migration"


    description = "Kotlin/Java 项目的数据migration工具"

    (plugins) {

        "migrationPlugin" {
            displayName = "Gradle Project Devtools"
            tags = listOf("individual", "gradle", "migration", "plugin", "dev", "tools")
            version = project.version.toString()
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.rootProject.name
            version = project.version.toString()

            from(components["kotlin"])
        }
    }

    repositories {
        mavenLocal()
    }

}

sonarqube {
    properties {
        property("sonar.projectKey", "JavaDream_migration")
        property("sonar.organization", "javadream")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}





