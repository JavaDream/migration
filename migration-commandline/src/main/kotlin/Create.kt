import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.default
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Create : CliktCommand(help="创建迁移脚本") {
    private val path = "/tmp" //未来要改成项目目录
    private val name by argument().default("migration")

    override fun run() {
        var file = File(fileName())
        file.writeText("migration")
    }

    private fun fileName() : String {
        return "$path/${now()}_$name.kt"
    }

    private fun now() : String {
        val now = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("YYYYMMDDHHMMSS")
        return now.format(formatter)
    }
}
