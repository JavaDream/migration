import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class TableTest : FunSpec({
    lateinit var table: Table

    beforeEach {
        table = Table("test_table")
    }

    test("能正确的添加BigInt列") {
        table.bigInt("bigint_column")

        table.toSql() shouldBe """
            CREATE TABLE test_table (
                bigint_column bigint
            )
        """.trimIndent()
    }
})
