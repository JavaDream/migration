package table

import formatSql
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import jdbc.Database

class TableTest : FunSpec({
    lateinit var table: Table

    beforeEach {
        table = Table("test_table")
    }

    test("bigInt 函数能正确的添加BigInt列") {
        table.column.bigInt("bigint_column")

        table.toSql() shouldBe """
            CREATE TABLE test_table (
                bigint_column bigint
            )
        """.formatSql()
    }

    test("executeToDatabase 能在数据库中正确的创建表") {
        val t = Table("test_table")
        t.column.bigInt("bigint_column")
        t.executeToDatabase()
        val database = Database.instance
        database.tableExists("test_table").shouldBeTrue()
    }
})
