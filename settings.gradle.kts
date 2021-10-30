rootProject.name = "migration"
include("migration-core")
include("migration-commandline")

pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}