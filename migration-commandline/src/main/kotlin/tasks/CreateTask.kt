package tasks

import camelize
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.sql.DriverManager
import java.time.LocalDateTime

open class CreateTask : BaseTask() {

    @Input
    val migrationName = "migration"

    private fun createFile() = File(fileName()).writeText("migration")

    private fun fileName(): String {
        val file = File(config.migrationPath())
        file.mkdir()
        return config.migrationPath("${now()}_${migrationName.camelize()}.kt")
    }

    private fun now(): String  = config.dateFormat(LocalDateTime.now())

    private fun createMigrationTable() {
        Command().createTable("migrations") {
            column string "name"
        }.executeToDatabase()
        DriverManager.getConnection("jdbc:sqlite:file::memory:?cache=shared")
    }

    @TaskAction
    fun create() {
        createFile()
        createMigrationTable()
    }
}




