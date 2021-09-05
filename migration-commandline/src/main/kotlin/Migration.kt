import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import jdbc.Database
import java.time.format.DateTimeFormatter

class Migration : CliktCommand() {
    companion object {
        const val path = "/tmp/migrations" //未来要改成项目目录
        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("YYYYMMDDHHMMSS")
    }

    override fun run() = Unit
}

fun main(args: Array<String>) = Migration().subcommands(Create()).main(args)