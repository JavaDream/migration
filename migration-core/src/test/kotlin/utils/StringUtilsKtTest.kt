package utils

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class StringUtilsKtTest : DescribeSpec({

    describe("camelize") {
        it("当只有一个单词的时候能正确的把首个字母大写") {
            "aBc".camelize() shouldBe "Abc"
        }

        it("当有多个单词的时候，能去掉中间的下划线，并且每个单词的首字母大写") {
            "abc_edf".camelize() shouldBe "AbcEdf"
        }
    }
})