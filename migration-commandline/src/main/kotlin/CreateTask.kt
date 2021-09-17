import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import tasks.Command
import java.io.File
import java.time.LocalDateTime

class CreateTask : DefaultTask() {
    @Input
    val migrationName = "migration"

    @TaskAction
    fun run() {
        createFile()
        createMigrationTable()
    }

    private fun createFile() {
        var file = File(fileName())
        file.writeText("migration")
    }

    private fun fileName() : String {
        val file = File(Migration.path)
        file.mkdir()
        return "${Migration.path}/${now()}_${migrationName.camelize()}.kt"
    }

    private fun now() : String {
        val now = LocalDateTime.now()
        return now.format(Migration.dateFormatter)
    }

    private fun createMigrationTable() {
        Command().createTable("migrations") {
            column string "name"
        }.executeToDatabase()
    }
}
