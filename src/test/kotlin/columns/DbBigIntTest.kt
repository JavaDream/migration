package columns

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


internal class DbBigIntTest {

    @Test
    @DisplayName("能正确生成sql")
    fun shouldGenerateSQLCorrectly() {
        val column = DbBigInt("test_bigint_column")
        assertEquals(
            column.toSql(),
            "test_bigint_column bigint"
        )
    }
}