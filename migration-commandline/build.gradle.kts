plugins {
    id("java-gradle-plugin")
}

dependencies {
    implementation(gradleApi())
    implementation(project(":migration-core"))
    implementation("org.xerial:sqlite-jdbc:3.36.0.2")
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

gradlePlugin {
    plugins {
        create("migrationPlugin") {
            id = "info.dreamcoder.migration"
            implementationClass = "CreatePlugin"
        }
    }
}

