package columns

import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

@DisplayName("测试类 DbString")
class DbStringTest : DescribeSpec({

    describe("toSql方法") {
        it("默认生成250 varchar类型的列") {
            val column = DbString("test_column")
            column.toSql() shouldBe "test_column varchar(250)"
        }
    }

})
