package info.dreamcoder.jdbc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    @DisplayName("能正确执行sql")
    void shouldExecuteSql() {
    }

    @Test
    @DisplayName("能获取数据库连接状态")
    void isValid() {
        assertTrue(database.isValid());
    }
}
