

plugins {
    kotlin("jvm")
    jacoco
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.ajalt.clikt:clikt:3.2.0")
    implementation(project(":migration-core"))

    testImplementation("org.xerial:sqlite-jdbc:3.36.0.2")
    testImplementation("io.kotest:kotest-runner-junit5:${project.properties["kotestVersion"]}")
    testImplementation("io.kotest:kotest-assertions-core:${project.properties["kotestVersion"]}")
    testImplementation("io.kotest:kotest-property:${project.properties["kotestVersion"]}")
}

tasks.test {
    useJUnitPlatform()
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