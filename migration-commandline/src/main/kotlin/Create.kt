import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.default
import tasks.Command
import utils.camelize
import java.io.File
import java.time.LocalDateTime

class Create : CliktCommand(help="创建迁移脚本") {
    private val name by argument().default("migration")

    override fun run() {
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
        return "${Migration.path}/${now()}_${name.camelize()}.kt"
    }

    private fun now() : String {
        val now = LocalDateTime.now()
        return now.format(Migration.dateFormatter)
    }

    private fun createMigrationTable() {
        Command().createTable("migrations") {
            column string "name"
        }
    }
}
