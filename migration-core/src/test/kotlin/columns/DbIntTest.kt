package columns

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


internal class DbIntTest {

    @Test
    @DisplayName("能正确生成sql")
    fun shouldGenerateSQLCorrectly() {
        val column = DbInt("test_int_column")
        assertEquals(
            column.toSql(),
            "test_int_column int"
        )
    }
}
