package table

import columns.Builder
import com.github.vertical_blank.sqlformatter.SqlFormatter

class Table(private val name: String) {
    val column = Builder()
    val option = Option()

    fun toSql() : String {
        return formatSql("""
            CREATE TABLE $name (
                ${column.toSql()}
            )
        """.trimIndent())
    }
}

fun createTable(name: String, block: Table.() -> Unit) : Table {
    val table = Table(name)
    table.block()
    return table
}

fun formatSql(sql: String) : String {
    return SqlFormatter.format(sql)
}
