
val kotestVersion = "4.6.1"

plugins {
//    kotlin("jvm") version "1.5.30" 这个地方指定了版本竟然还不行，看网上是说同一个想不里面不允许出现不同的版本，但是这个项目里面出现的都是1.5.30啊
    kotlin("jvm")
    jacoco
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("com.github.vertical-blank:sql-formatter:2.0.2")

    testImplementation("mysql:mysql-connector-java:8.0.26")
    testImplementation("org.xerial:sqlite-jdbc:3.36.0.2")
    testImplementation("org.assertj:assertj-db:2.0.2")
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    testImplementation("io.mockk:mockk:1.12.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>() {
    kotlinOptions.jvmTarget = "14"
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        csv.required.set(true)
    }
}