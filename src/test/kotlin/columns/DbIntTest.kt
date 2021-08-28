package columns

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class DbIntTest : DescribeSpec({

    it("能正确生成sql") {
        val column = DbInt("test_int_column")
        column.toSql() shouldBe "test_int_column int"
    }

})
