package table

import columns.Builder
import com.github.vertical_blank.sqlformatter.SqlFormatter
import jdbc.Database
import utils.formatSql

class Table(private val name: String) {
    val column = Builder()
    val option = Option()

    fun toSql() : String {
        return """
            CREATE TABLE $name (
                ${column.toSql()}
            )
        """.formatSql()
    }
}
