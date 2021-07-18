package info.dreamcoder;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static info.dreamcoder.Migration.createTable;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("表测试")
class TestTable {

    @Test
    @DisplayName("能正常创建表")
    void shouldCreateTable() {
        Table table = createTable("test_table", (t) -> {
            t.string("test_string_column");
        });
        assertEquals(
                table.toSql(), "CREATE TABLE test_table\n" +
                        "(\n" +
                        "test_string_column varchar(255)\n" +
                        ")"
        );
    }
}
