package columns

import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

@DisplayName("测试类 DbString")
class DbStringTest : DescribeSpec({

    lateinit var column: DbString

    beforeEach {
        column = DbString("test_column")
    }

    describe("toSql方法") {
        it("默认生成250 varchar类型的列") {
            column.toSql() shouldBe "test_column varchar(250)"
        }
    }

    describe("limit 方法") {
        it("能修改varchar类型的长度") {
            column.limit(100)
            column.toSql() shouldBe "test_column varchar(100)"
        }
    }

})
