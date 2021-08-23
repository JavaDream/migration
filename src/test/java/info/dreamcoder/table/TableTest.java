package info.dreamcoder.table;

import info.dreamcoder.config.Config;
import info.dreamcoder.config.Factory;
import info.dreamcoder.jdbc.Database;
import org.assertj.db.type.Request;
import org.assertj.db.type.Source;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static info.dreamcoder.Migration.createTable;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.db.api.Assertions.*;


@DisplayName("表测试")
class TableTest {

    private Database database;
    private static Source source;

    @BeforeAll
    static void createDbSource() {
        Config config = Factory.getConfig();
        source = new Source(
                config.getDbUrl(),
                config.getDbUserName(),
                config.getPassword()
        );
    }

    @BeforeEach
    public void createDatabase() {
        try {
            database = new Database();
            database.execute("drop table if exists test_table;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void closeDatabase() {
        database.close();
    }

    private void checkTable(Table table) {

        Request request = new Request(source, "show tables like '%test_table%'");
        assertThat(request).row(0)
                .value().isEqualTo("test_table");
    }

    @Test
    @DisplayName("能正常创建表")
    void couldCreateTable() throws SQLException {
        Table table = createTable("test_table", (t) -> {
            t.string("test_string_column");
            t.integer("test_integer_column");
            t.timestamps();
        });
        assertThat(table.toSql()).isEqualTo("CREATE TABLE test_table\n" +
                        "(\n" +
                        "id bigint auto_increment PRIMARY KEY,\n" +
                        "test_string_column varchar(255),\n" +
                        "test_integer_column int,\n" +
                        "created_at timestamp,\n" +
                        "updated_at timestamp\n" +
                        ")"
        );
        checkTable(table);
    }

    @Test
    @DisplayName("在创建表的时候，可以指定是否需要主键ID")
    void couldDisableIdWhenCreateTable() throws SQLException {
        TableOptions options = new TableOptions();
        options.disableId();

        Table table = createTable("test_table", options, (t) -> {
            t.string("test_string_column");
            t.integer("test_integer_column");
            t.timestamps();
        });

        assertThat(table.toSql()).isEqualTo("CREATE TABLE test_table\n" +
                        "(\n" +
                        "test_string_column varchar(255),\n" +
                        "test_integer_column int,\n" +
                        "created_at timestamp,\n" +
                        "updated_at timestamp\n" +
                        ")"
        );
        checkTable(table);
    }

    @Test
    @DisplayName("在创建表的时候，可以设置主键ID的名字")
    void couldSetPrimaryKeyWhenCreateTable() throws SQLException {
        TableOptions options = new TableOptions();
        options.setPrimaryKey("test_primary_key");

        Table table = createTable("test_table", options, (t) -> {
            t.string("test_string_column");
            t.integer("test_integer_column");
            t.timestamps();
        });

        assertThat(table.toSql()).isEqualTo("CREATE TABLE test_table\n" +
                        "(\n" +
                        "test_primary_key bigint auto_increment PRIMARY KEY,\n" +
                        "test_string_column varchar(255),\n" +
                        "test_integer_column int,\n" +
                        "created_at timestamp,\n" +
                        "updated_at timestamp\n" +
                        ")"
        );
        checkTable(table);
    }

    @Test
    @DisplayName("在创建表的时候，可以使用字符串类型的主键")
    void couldUseStringPrimaryKeyWhenCreateTable() throws SQLException {
        TableOptions options = new TableOptions();
        options.setPrimaryKey("test_primary_key");
        options.setIdType("string");

        Table table = createTable("test_table", options, (t) -> {
            t.string("test_string_column");
            t.integer("test_integer_column");
            t.timestamps();
        });

        assertThat(table.toSql()).isEqualTo("CREATE TABLE test_table\n" +
                        "(\n" +
                        "test_primary_key varchar(255) PRIMARY KEY,\n" +
                        "test_string_column varchar(255),\n" +
                        "test_integer_column int,\n" +
                        "created_at timestamp,\n" +
                        "updated_at timestamp\n" +
                        ")"
        );
        checkTable(table);
    }
}
