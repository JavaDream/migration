import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands

class Migration : CliktCommand() {
    companion object {
        val path = "/tmp" //未来要改成项目目录
    }

    override fun run() = Unit
}

fun main(args: Array<String>) = Migration().subcommands(Create()).main(args)