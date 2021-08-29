package jdbc

import config.DbConfig
import config.DbType
import java.sql.DriverManager
import java.sql.ResultSet

class Database(private val dbType: DbType) {
    private val config = DbConfig(dbType)

    private val dbConnection = DriverManager.getConnection(
        config.dbUrl,
        config.dbUsername,
        config.dbPassword
    )

    private val statement = dbConnection.createStatement()

    fun isValid(): Boolean {
        return dbConnection.runCatching { isValid(1000) }.getOrElse { false }
    }

    fun close() = dbConnection.close()

    fun execute(sql: String) {
        statement.execute(sql)
    }

    fun query(sql: String): ResultSet {
        return statement.executeQuery(sql)
    }

    fun tableExists(name: String) : Boolean {
        val sql = when(dbType) {
            DbType.Sqlite -> "select name from sqlite_master where type='table' and name='$name'"
            DbType.Mysql -> "show tables like '%$name%' "
        }
        val rs = query(sql)

        return rs.next() && rs.getString(1) == name
    }
}