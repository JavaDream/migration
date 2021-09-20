plugins {
    id("java-gradle-plugin")
}

dependencies {
    implementation(gradleApi())
    implementation(project(":migration-core"))
    implementation("org.xerial:sqlite-jdbc:3.36.0.2")
}

gradlePlugin {
    plugins {
        create("migrationPlugin") {
            id = "info.dreamcoder.migration"
            implementationClass = "Migration"
        }
    }
}

