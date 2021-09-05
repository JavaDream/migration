package table

import columns.ColumnType
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import jdbc.Database
import utils.formatSql

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
})
