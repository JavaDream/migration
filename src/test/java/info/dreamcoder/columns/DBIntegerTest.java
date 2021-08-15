package info.dreamcoder.columns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("测试整数类型的列")
class DBIntegerTest {

    @Test
    @DisplayName("能正确的创建整形的列")
    void shouldAddIntegerColumn() {
        DBInteger col = new DBInteger("int_column");
        assertThat(col.sql()).isEqualTo("int_column int");
    }
}