plugins {
    id("java-gradle-plugin")
    id("com.gradle.plugin-publish") version "0.16.0"
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

