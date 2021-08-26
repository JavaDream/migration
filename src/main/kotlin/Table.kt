import columns.BigInt
import columns.Builder

class Table(private val name: String) {
    private val columnBuilder = Builder()

    fun bigInt(name: String) = columnBuilder.addColumn(BigInt(name))

    fun toSql() : String {
        return """
            CREATE TABLE $name (
                ${columnBuilder.toSql()}
            )
        """.trimIndent()
    }
}

//fun createTable(name: String, block: Table.() -> Unit) {
//
//}
