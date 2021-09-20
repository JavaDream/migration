package table

import formatSql

import jdbc.Database
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


class TableTest {
    lateinit var table: Table

    @BeforeEach
    fun initTable() {
        table = Table("test_table")
    }

    @Test
    @DisplayName("bigInt 函数能正确的添加BigInt列")
    fun couldAddBigIntColumn() {
        table.column.bigInt("bigint_column")
        table.toSql() shouldBeEqualTo  """
            CREATE TABLE test_table (
                bigint_column bigint
            )
        """.formatSql()
    }

    @Test
    @DisplayName("executeToDatabase 能在数据库中正确的创建表")
    fun shouldCreateTableWhenCallExecuteToDatabase() {
        val t = Table("test_table")
        t.column.bigInt("bigint_column")
        t.executeToDatabase()
        val database = Database.instance
        database.tableExists("test_table").shouldBeTrue()
    }
}