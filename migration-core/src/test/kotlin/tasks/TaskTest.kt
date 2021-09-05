package tasks

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class TaskTest : FunSpec({
    val t = Task("202109248120969")


    test("up 和 down 方法") {
        t.up { this.shouldBeInstanceOf<Command>() }
        t.down { this.shouldBeInstanceOf<Command>() }
    }

    test("version") {
        t.version shouldBe "202109248120969"
    }
})
