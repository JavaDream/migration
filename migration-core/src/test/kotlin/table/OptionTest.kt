package table

import columns.ColumnType
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class OptionTest : DescribeSpec({

    lateinit var option: Option

    beforeEach {
        option = Option()
    }

    describe("id配置") {
        it("默认值是true") {
            option.needId shouldBe true
        }

        it("能关闭id") {
            option id false
            option.needId shouldBe false
        }

        it("能正确设置id列的名字") {
            option id ColumnType.String
            option.idType shouldBe ColumnType.String
        }
    }

    describe("primaryKey配置") {
        it("默认名字是id") {
            option.primaryKey shouldBe "id"
        }

        it("能正确配置名字") {
            option primaryKey "test_key"
            option.primaryKey shouldBe "test_key"
        }
    }

    describe("options配置") {
        it("默认值是空字符串") {
            option.options shouldBe ""
        }

        it("能正确配置") {
            option options "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4"
            option.options shouldBe "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4"
        }
    }

    describe("force配置") {
        it("默认值是false") {
            option.force shouldBe false
        }

        it("能正确配置") {
            option force true
            option.force shouldBe true
        }
    }

    describe("asSql配置") {
        it("默认值是空字符串") {
            option.asSql shouldBe ""
        }

        it("能正确配置") {
            option asSql "SELECT * FROM orders INNER JOIN line_items ON order_id=orders.id"
            option.asSql shouldBe "SELECT * FROM orders INNER JOIN line_items ON order_id=orders.id"
        }
    }
})
