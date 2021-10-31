package tasks

import table.Table

class Command {
    fun createTable(name: String, block: Table.() -> Unit) : Table {
        val table = Table(name)
        table.block()
        return table
    }
}
