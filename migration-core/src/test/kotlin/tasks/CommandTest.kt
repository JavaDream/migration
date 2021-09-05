package tasks

import columns.ColumnType
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.shouldBe
import jdbc.Database
import utils.formatSql

class CommandTest : FunSpec({

    test("createTable 函数能正确的创建表格") {
        val t = Command().createTable("test_table") {
            option id true id ColumnType.String
            option options "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4"

            column bigInt "bigint_column"
            column string "string_column" limit 100
        }
        t.toSql() shouldBe """
            CREATE TABLE test_table (
                bigint_column bigint,
                string_column varchar(100)
            )
        """.formatSql()
        Database().tableExists("test_table").shouldBeFalse()
    }
})
