import org.gradle.api.Plugin
import org.gradle.api.Project
import tasks.Command
import java.io.File
import java.sql.DriverManager
import java.time.LocalDateTime

open class CreatePlugin : Plugin<Project> {
    private val migrationName = "migration"

    fun createFile() {
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

    fun createMigrationTable() {
//         由于Gradle不能自动加载数据库的驱动，所以这儿要手动加载, 暂时先加载sqlite的，未来还要加载mysql等各种支持的数据库
        Class.forName("org.sqlite.JDBC")
        Command().createTable("migrations") {
            column string "name"
        }.executeToDatabase()
        DriverManager.getConnection("jdbc:sqlite:file::memory:?cache=shared")
    }

    override fun apply(target: Project) {
        target.task("migration.create") {
            createFile()
            createMigrationTable()
        }
    }
}

fun main() {
//    DriverManager.getConnection("jdbc:sqlite:file::memory:?cache=shared")
    println(DriverManager.drivers().count())
    DriverManager.drivers().forEach {
        println(it.toString())
    }
    Class.forName("org.sqlite.JDBC");
}




