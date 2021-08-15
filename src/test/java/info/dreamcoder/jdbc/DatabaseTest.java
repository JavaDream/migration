package info.dreamcoder.jdbc;

import org.assertj.db.type.Request;
import org.assertj.db.type.Source;
import org.junit.jupiter.api.*;

import org.mockito.internal.util.reflection.FieldSetter;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.db.api.Assertions.*;

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
        assertThatNoException().isThrownBy(() -> database.execute("show tables"));
        assertThat(database.execute("show tables")).isTrue();
    }

    @Test
    @DisplayName("sql错误的时候,得到false的结果")
    void shouldGetFalseWheSqlHasError() {
        assertThat(database.execute("show tables 123")).isFalse();
    }


    @Test
    @DisplayName("能正确创建表格")
    void shouldCreateTable() throws SQLException {
        assertThat(database.execute("create table test_table(id integer)")).isTrue();

        Source source = new Source(
                System.getenv("MYSQL_URL"),
                System.getenv("MYSQL_USERNAME"),
                System.getenv("MYSQL_PASSWORD")
        );

        Request request = new Request(source, "show tables like '%test_table%'");

        assertThat(request).row(0)
                .value().isEqualTo("test_table");
    }

    @Test
    @DisplayName("可以正常的删除表格")
    void shouldDropTable() {
        assertThat(database.execute("create table test_table(id integer)")).isTrue();
    }

    @Test
    @DisplayName("能获取数据库连接状态")
    void shouldGetTrueWhenConnectionIsSuccess() {
        assertThat(database.isValid()).isTrue();
    }

    @Test
    @DisplayName("数据库连接有问题的时候返回false")
    void shouldGetFalseWhenConnectionIsError() throws NoSuchFieldException, SQLException {
        Connection connection = mock(Connection.class);
        FieldSetter.setField(database, database.getClass().getDeclaredField("dbConnection"), connection);
        when(connection.isValid(1000)).thenThrow(SQLException.class);
        assertThat(database.isValid()).isFalse();
    }
}
