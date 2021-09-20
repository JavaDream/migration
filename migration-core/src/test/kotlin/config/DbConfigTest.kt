package config

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class DbConfigTest {

    @Test
    @DisplayName("当使用mysql的时候，能准确从环境变量读取")
    fun shouldReadMysqlConfigFromEnv() {
        val config = DbConfig(DbType.Mysql)
        assertEquals(
            config.dbUrl,
            System.getenv("MYSQL_URL")
        )
    }
}
