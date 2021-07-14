package info.dreamcoder.jdbc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    @Test
    @DisplayName("能正确执行sql")
    void shouldExecuteSql() throws SQLException {
        assertTrue(database.execute("show tables"));
        assertTrue(database.execute("drop table if exists test_table"));
        assertTrue(database.execute("create table test_table(id integer)"));

        ResultSet rs = database.query("show tables like '%test_table%'");
        assertTrue(rs.next());
        assertEquals(rs.getString(1), "test_table");
        rs.getStatement().close();
        rs.close();

        assertTrue(database.execute("drop table test_table"));
    }

    @Test
    @DisplayName("能获取数据库连接状态")
    void isValid() {
        assertTrue(database.isValid());
    }
}
