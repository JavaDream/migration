package info.dreamcoder;

import org.junit.jupiter.api.Test;

import static info.dreamcoder.Migration.createTable;
import static org.junit.jupiter.api.Assertions.*;


public class AppTest {


    @Test
    public void shouldCreateTable() {
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
