package info.dreamcoder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static info.dreamcoder.Migration.createTable;
import static org.assertj.core.api.Assertions.*;


@DisplayName("表测试")
class TestTable {

    @Test
    @DisplayName("能正常创建表")
    void couldCreateTable() {
        Table table = createTable("test_table", (t) -> {
            t.string("test_string_column");
            t.integer("test_integer_column");
            t.timestamps();
        });
        assertThat(table.toSql()).isEqualTo("CREATE TABLE test_table\n" +
                        "(\n" +
                        "id bigint auto_increment PRIMARY KEY\n" +
                        "test_string_column varchar(255)\n" +
                        "test_integer_column int\n" +
                        "created_at timestamp\n" +
                        "updated_at timestamp\n" +
                        ")"
        );
    }

    @Test
    @DisplayName("在创建表的时候，可以指定是否需要主键ID")
    void couldDisableIdWhenCreateTable() {
        TableOptions options = new TableOptions();
        options.disableId();

        Table table = createTable("test_table", options, (t) -> {
            t.string("test_string_column");
            t.integer("test_integer_column");
            t.timestamps();
        });

        assertThat(table.toSql()).isEqualTo("CREATE TABLE test_table\n" +
                        "(\n" +
                        "test_string_column varchar(255)\n" +
                        "test_integer_column int\n" +
                        "created_at timestamp\n" +
                        "updated_at timestamp\n" +
                        ")"
        );
    }

    @Test
    @DisplayName("在创建表的时候，可以设置主键ID的名字")
    void couldSetPrimaryKeyWhenCreateTable() {
        TableOptions options = new TableOptions();
        options.setPrimaryKey("test_primary_key");

        Table table = createTable("test_table", options, (t) -> {
            t.string("test_string_column");
            t.integer("test_integer_column");
            t.timestamps();
        });

        assertThat(table.toSql()).isEqualTo("CREATE TABLE test_table\n" +
                        "(\n" +
                        "test_primary_key bigint auto_increment PRIMARY KEY\n" +
                        "test_string_column varchar(255)\n" +
                        "test_integer_column int\n" +
                        "created_at timestamp\n" +
                        "updated_at timestamp\n" +
                        ")"
        );
    }

    @Test
    @DisplayName("在创建表的时候，可以使用字符串类型的主键")
    void couldUseStringPrimaryKeyWhenCreateTable() {
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
                        "test_primary_key varchar(255) PRIMARY KEY\n" +
                        "test_string_column varchar(255)\n" +
                        "test_integer_column int\n" +
                        "created_at timestamp\n" +
                        "updated_at timestamp\n" +
                        ")"
        );
    }
}
