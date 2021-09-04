import com.github.ajalt.clikt.core.CliktCommand
import java.io.File

class Up : CliktCommand(help="创建迁移脚本") {
    override fun run() {
        TODO("Not yet implemented")
    }

    private fun versionList() : List<String> {
        return File(Migration.path)
            .walk()
            .filter { it.extension == "kt" }
            .map { it.name }.toList()
    }

    private fun currentVersion() {

    }
}
