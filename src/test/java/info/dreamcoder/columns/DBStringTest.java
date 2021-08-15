package info.dreamcoder.columns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("测试字符串类型的列")
class DBStringTest {

    @Test
    @DisplayName("能正确的创建字符串的类型，默认是255")
    void shouldCreateStringColumn() {
        DBString col = new DBString("string_column");
        assertThat(col.toSql()).isEqualTo("string_column varchar(255)");
    }
}