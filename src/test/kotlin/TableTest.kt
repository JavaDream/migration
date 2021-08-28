import com.github.vertical_blank.sqlformatter.SqlFormatter
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class TableTest : FunSpec({
    lateinit var table: Table

    beforeEach {
        table = Table("test_table")
    }

    test("bigInt 函数能正确的添加BigInt列") {
        table.bigInt("bigint_column")

        table.toSql() shouldBe formatSql("""
            CREATE TABLE test_table (
                bigint_column bigint
            )
        """)
    }

    test("createTable 函数能正确的创建表格") {
        val t = createTable("test_table") {
            bigInt("bigint_column")
            string("string_column")
        }
        t.toSql() shouldBe formatSql("""
            CREATE TABLE test_table (
                bigint_column bigint,
                string_column varchar(250)
            )
        """)
    }
})
