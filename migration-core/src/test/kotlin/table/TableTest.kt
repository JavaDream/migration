package table

import columns.ColumnType
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

        table.toSql() shouldBe formatSql("""
            CREATE TABLE test_table (
                bigint_column bigint
            )
        """)
    }

    test("createTable 函数能正确的创建表格") {
        val t = createTable("test_table") {
            option id true id ColumnType.String
            option options "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4"

            column bigInt "bigint_column"
            column string "string_column" limit 100
        }
        t.toSql() shouldBe formatSql("""
            CREATE TABLE test_table (
                bigint_column bigint,
                string_column varchar(100)
            )
        """)
        Database().tableExists("test_table").shouldBeTrue()
    }
})
