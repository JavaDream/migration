package info.dreamcoder.columns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("测试timestamp类型的列")
class DBTimestampTest {

    @Test
    @DisplayName("能正确的创建timestamp类型的列")
    void shouldCreateTimestampColumn() {
        DBTimestamp col = new DBTimestamp("timestamp_column");
        assertEquals(col.toSql(), "timestamp_column timestamp");
    }
}