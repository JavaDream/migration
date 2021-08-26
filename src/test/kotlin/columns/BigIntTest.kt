package columns

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class BigIntTest : FunSpec({

    test("toSql") {
        val bigInt = BigInt("test_bigint_column")
        bigInt.toSql() shouldBe "test_bigint_column bigint"
    }
})
