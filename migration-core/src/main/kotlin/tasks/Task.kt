package tasks

class Task(val version: String) {
    fun up(block: Command.() -> Unit) {
        val command = Command()
        command.block()
    }

    fun down(block: Command.() -> Unit) = up(block)
}
