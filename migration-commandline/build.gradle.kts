plugins {
    id("java-gradle-plugin")
}

dependencies {
    implementation(gradleApi())
    implementation(project(":migration-core"))
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
            implementationClass = "CreateTask"
        }
    }
}

