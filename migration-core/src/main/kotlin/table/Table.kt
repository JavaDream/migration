package table

import columns.Builder
import formatSql
import jdbc.Database

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

    fun executeToDatabase() {
        val database = Database.instance
        if(!database.tableExists(name)) {
            database.execute(toSql())
        }
    }
}
