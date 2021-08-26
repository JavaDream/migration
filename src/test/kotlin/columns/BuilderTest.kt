package columns

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class BuilderTest : FunSpec({
    var builder: Builder = Builder()

    test("能正确添加列") {
        builder.addColumn(BigInt("bigint_column1"))
        builder.addColumn(BigInt("bigint_column2"))

        builder.toSql() shouldBe """
            bigint_column1 bigint
            bigint_column2 bigint
        """.trimIndent()
    }
})
