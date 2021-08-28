package columns

class Builder {
    private val columns = mutableListOf<Column>()

    fun addColumn(column: Column) {
        columns.add(column)
    }

    fun toSql(): String {
        return columns.joinToString(separator = ",\n") { it.toSql() }
    }
}
