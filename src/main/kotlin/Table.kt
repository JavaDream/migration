import columns.DbBigInt
import columns.Builder
import columns.DbString
import com.github.vertical_blank.sqlformatter.SqlFormatter

class Table(private val name: String) {
    private val columnBuilder = Builder()

    fun bigInt(name: String) = columnBuilder.addColumn(DbBigInt(name))

    fun string(name: String) = columnBuilder.addColumn(DbString(name))

    fun toSql() : String {
        return formatSql("""
            CREATE TABLE $name (
                ${columnBuilder.toSql()}
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
