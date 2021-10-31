package columns

class Builder {
    private val columns = mutableListOf<Column>()

    infix fun bigInt(name: String): DbBigInt {
        val column = DbBigInt(name)
        addColumn(column)
        return column
    }

    infix fun string(name: String): DbString {
        val column = DbString(name)
        addColumn(column)
        return column
    }

    infix fun int(name: String): DbInt {
        val column = DbInt(name)
        addColumn(column)
        return column
    }

    private fun addColumn(column: Column) {
        columns.add(column)
    }

    fun toSql(): String {
        return columns.joinToString(separator = ",\n") { it.toSql() }
    }
}
