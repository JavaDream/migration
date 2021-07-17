package info.dreamcoder.jdbc;

import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("数据库操作相关测试")
class DatabaseTest {
    static Database database;

    @BeforeAll
    public static void createDatabase() {
        try {
            database = new Database(
                    System.getenv("MYSQL_URL"),
                    System.getenv("MYSQL_USERNAME"),
                    System.getenv("MYSQL_PASSWORD")
            );
            database.execute("drop table if exists test_table;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void closeDatabase() {
        try {
            database.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void cleanDatabase() {
        database.execute("drop table if exists test_table;");
    }

    @Test
    @DisplayName("可以正常的执行show table语句")
    void shouldExecuteShowTableSql() {
        assertDoesNotThrow(() -> {
            database.execute("show tables");
        });
        database.execute("show tables", (result) -> {
            assertTrue(result);
        });
    }

    @Test
    @DisplayName("能正确创建表格")
    void shouldCreateTable() {
        database.execute("create table test_table(id integer)", (result) -> {
            assertTrue(result);
        });
        database.query("show tables like '%test_table%'", (rs) -> {
            assertDoesNotThrow(() -> {
                rs.next();
            });
            assertDoesNotThrow(() -> {
                String tableName = rs.getString(1);
                assertEquals("test_table", tableName);
            });
        });
    }

    @Test
    @DisplayName("可以正常的删除表格")
    void shouldDropTable() {
        database.execute("create table test_table(id integer)");
        database.execute("drop table test_table", (result) -> {
            assertTrue(result);
        });
    }

    @Test
    @DisplayName("能获取数据库连接状态")
    void isValid() {
        assertTrue(database.isValid());
    }
}
