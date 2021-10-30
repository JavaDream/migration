package tasks

import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.time.LocalDateTime
import MigrationVersion

open class CreateTask : BaseTask() {

    @Input
    val migrationName = "migration"

    private fun createFile() {
        val version = MigrationVersion(migrationName)
        File(version.filePath()).writeText(migrationText())
    }

    private fun now(): String  = MigrationConfig.config.dateFormat(LocalDateTime.now())

    private fun createMigrationTable() {
        Command().createTable("migrations") {
            column string "name"
        }.executeToDatabase()
    }

    private fun migrationText(): String {
        return """
            package migrations
            
        """.trimIndent()
    }

    @TaskAction
    fun create() {
        createFile()
        createMigrationTable()
    }
}




