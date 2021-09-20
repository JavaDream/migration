package tasks

import camelize
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.sql.DriverManager
import java.time.LocalDateTime

open class CreateTask : DefaultTask() {
    @Input
    val migrationName = "migration"

    private fun createFile() {
        var file = File(fileName())
        file.writeText("migration")
    }

    private fun fileName() : String {
        val file = File(MigrationConfig.path)
        file.mkdir()
        return "${MigrationConfig.path}/${now()}_${migrationName.camelize()}.kt"
    }

    private fun now() : String {
        val now = LocalDateTime.now()
        return now.format(MigrationConfig.dateFormatter)
    }

    private fun createMigrationTable() {
//         由于Gradle不能自动加载数据库的驱动，所以这儿要手动加载, 暂时先加载sqlite的，未来还要加载mysql等各种支持的数据库
        Class.forName("org.sqlite.JDBC")
        Command().createTable("migrations") {
            column string "name"
        }.executeToDatabase()
        DriverManager.getConnection("jdbc:sqlite:file::memory:?cache=shared")
    }

    @TaskAction
    fun run() {
        createFile()
        createMigrationTable()
    }
}




