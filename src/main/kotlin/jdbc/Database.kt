package jdbc

import config.DbConfig
import config.DbType
import java.sql.DriverManager
import java.sql.ResultSet

class Database(dbType: DbType) {
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
}