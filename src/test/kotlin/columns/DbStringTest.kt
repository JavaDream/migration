package columns

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DbStringTest {

    private lateinit var column: DbString

    @BeforeEach
    fun initColumn() {
        column = DbString("test_column");
    }

    @Test
    @DisplayName("默认生成250 varchar类型的列")
    fun shouldCreateVarcharColumnWith250Length() {
        assertEquals(
            column.toSql(),
            "test_column varchar(250)"
        )
    }

    @Test
    @DisplayName("能修改varchar类型的长度")
    fun canChangeColumnLength() {
        column.limit(100)
        assertEquals(
            column.toSql(),
            "test_column varchar(100)"
        )
    }
}
