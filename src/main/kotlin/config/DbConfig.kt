package config

enum class DbType {Mysql, Sqlite}

class DbConfig(var dbType: DbType) {

    var dbUrl: String
        private set

    var dbUsername: String = ""
        private set

    var dbPassword: String = ""
        private set

    init {

        when(dbType) {
            DbType.Sqlite -> dbUrl = "jdbc:sqlite:file::memory:?cache=shared"
            DbType.Mysql -> {
                dbUrl = System.getenv("MYSQL_URL")
                dbUsername = System.getenv("MYSQL_USERNAME")
                dbPassword = System.getenv("MYSQL_PASSWORD")
            }
        }

    }
}

