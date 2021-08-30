import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands

class Migration : CliktCommand() {
    override fun run() = Unit
}

fun main(args: Array<String>) = Migration().subcommands(Create()).main(args)