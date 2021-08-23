package info.dreamcoder.table;

import info.dreamcoder.table.TableOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TableOptionsTest {
    private TableOptions options;

    @BeforeEach
    @AfterEach
    void createTableOptions() {
        options = new TableOptions();
    }

    @Test
    @DisplayName("测试配置的默认值是否正确")
    void testDefaultValue() {

        assertThat(options.getIdType()).isEqualTo("bigint");

        assertThat(options.isNeedId()).isTrue();
        assertThat(options.getPrimaryKey()).isEqualTo("id");
        assertThat(options.getOptions()).isEmpty();
        assertThat(options.getAs()).isEmpty();
    }

    @Test
    @DisplayName("可以设置是否需要ID")
    void couldDisableId() {
        options.disableId();
        assertThat(options.isNeedId()).isFalse();
    }

    @Test
    @DisplayName("可以设置主键名称")
    void couldSetPrimaryKey() {
        options.setPrimaryKey("test_primary_key");
        assertThat(options.getPrimaryKey()).isEqualTo("test_primary_key");
    }


    @Test
    @DisplayName("可以设置创建表额外的参数")
    void couldSetOptions() {
        options.setOptions("ENGINE=InnoDB DEFAULT CHARSET=utf8mb4");
        assertThat(options.getOptions()).isEqualTo("ENGINE=InnoDB DEFAULT CHARSET=utf8mb4");
    }


    @Test
    @DisplayName("可以设置As参数")
    void couldSetAs() {
        options.setAs("SELECT * FROM orders INNER JOIN line_items ON order_id=orders.id");
        assertThat(options.getAs()).isEqualTo("SELECT * FROM orders INNER JOIN line_items ON order_id=orders.id");
    }



    @Test
    @DisplayName("可以设置主键类型")
    void couldSetIdType() {
        options.setIdType("string");
        assertThat(options.getIdType()).isEqualTo("string");
    }
}