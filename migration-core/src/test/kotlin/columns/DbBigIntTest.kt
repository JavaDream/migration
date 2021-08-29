package columns

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class DbBigIntTest : DescribeSpec({

    it("能正确生成sql") {
        val column = DbBigInt("test_bigint_column")
        column.toSql() shouldBe "test_bigint_column bigint"
    }

})
