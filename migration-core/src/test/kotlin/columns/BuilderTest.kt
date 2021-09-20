package columns

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


internal class BuilderTest {

    @Test
    @DisplayName("能正确添加列")
    fun columnsCanBeAddedCorrectly() {
        var builder: Builder = Builder()

        builder bigInt "bigint_column1"
        builder bigInt "bigint_column2"

        assertEquals(
            builder.toSql(),
            """
                bigint_column1 bigint,
                bigint_column2 bigint
            """.trimIndent()
        )
    }
}