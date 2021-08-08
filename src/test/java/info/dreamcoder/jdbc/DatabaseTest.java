package info.dreamcoder.jdbc;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.*;
import org.mockito.internal.util.reflection.FieldSetter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("数据库操作相关测试")
class DatabaseTest {
    private Database database;

    @BeforeEach
    public void createDatabase() {
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

    @AfterEach
    public void closeDatabase() {
        database.close();
    }

    @BeforeEach
    public void cleanDatabase() throws SQLException {
        database.execute("drop table if exists test_table;");
    }

    @Test
    @DisplayName("可以正常的执行show table语句")
    void shouldExecuteShowTableSql() {
        assertDoesNotThrow(() -> {
            database.execute("show tables");
        });
        assertTrue(database.execute("show tables"));
    }

    @Test
    @DisplayName("sql错误的时候,得到false的结果")
    void shouldGetFalseWheSqlHasError() {
        assertFalse(database.execute("show tables 123"));
    }


    @Test
    @DisplayName("能正确创建表格")
    void shouldCreateTable() throws SQLException {
        assertTrue(database.execute("create table test_table(id integer)"));
        ResultSet rs = database.query("show tables like '%test_table%'");
        assertDoesNotThrow(rs::next);
        assertDoesNotThrow(() -> {
            String tableName = rs.getString(1);
            assertEquals("test_table", tableName);
        });
    }

    @Test
    @DisplayName("可以正常的删除表格")
    void shouldDropTable() {
        database.execute("create table test_table(id integer)");
        assertTrue(database.execute("drop table test_table"));
    }

    @Test
    @DisplayName("能获取数据库连接状态")
    void shouldGetTrueWhenConnectionIsSuccess() {
        assertTrue(database.isValid());
    }

    @Test
    @DisplayName("数据库连接有问题的时候返回false")
    void shouldGetFalseWhenConnectionIsError() throws NoSuchFieldException, SQLException {

        Connection connection = mock(Connection.class);
        FieldSetter.setField(database, database.getClass().getDeclaredField("dbConnection"), connection);
        when(connection.isValid(1000)).thenThrow(SQLException.class);
        assertFalse(database.isValid());
    }
}
