package tasks

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


class TaskTest {

    val t = Task("202109248120969")

    @Test
    @DisplayName("存在Up和Down方法")
    fun shouldExistsUpAndDownMethod() {
        t.up { this shouldBeInstanceOf Command::class }
        t.down { this shouldBeInstanceOf Command::class }
    }

    @Test
    @DisplayName("能正确得到版本")
    fun couldGetVersion() {
        t.version shouldBeEqualTo "202109248120969"
    }

}