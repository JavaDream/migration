package tasks

import columns.ColumnType
import formatSql
import jdbc.Database
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CommandTest {

    val database = Database()

    @BeforeEach
    fun initTable() {
        database.execute("drop table if exists test_table")
    }

    @Test
    @DisplayName("createTable 函数能正确的生成创建表格的sql")
    fun shouldCreateCorrectSqlWhenCallCreateTable() {

        val t = Command().createTable("test_table") {
            option id true id ColumnType.String
            option options "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4"

            column bigInt "bigint_column"
            column string "string_column" limit 100
        }
        t.toSql() shouldBeEqualTo  """
            CREATE TABLE test_table (
                bigint_column bigint,
                string_column varchar(100)
            )
        """.formatSql()

        Database().tableExists("test_table").shouldBeFalse()
    }
}