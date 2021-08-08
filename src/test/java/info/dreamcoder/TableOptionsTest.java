package info.dreamcoder;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals("bigint", options.getIdType());
        assertTrue(options.isNeedId());
        assertEquals("id", options.getPrimaryKey());
        assertEquals("", options.getOptions());
        assertEquals("", options.getAs());
    }

    @Test
    @DisplayName("可以设置是否需要ID")
    void couldDisableId() {
        options.disableId();
        assertFalse(options.isNeedId());
    }

    @Test
    @DisplayName("可以设置主键名称")
    void couldSetPrimaryKey() {
        options.setPrimaryKey("test_primary_key");
        assertEquals("test_primary_key", options.getPrimaryKey());
    }


    @Test
    @DisplayName("可以设置创建表额外的参数")
    void couldSetOptions() {
        options.setOptions("ENGINE=InnoDB DEFAULT CHARSET=utf8mb4");
        assertEquals("ENGINE=InnoDB DEFAULT CHARSET=utf8mb4", options.getOptions());
    }


    @Test
    @DisplayName("可以设置As参数")
    void couldSetAs() {
        options.setAs("SELECT * FROM orders INNER JOIN line_items ON order_id=orders.id");
        assertEquals("SELECT * FROM orders INNER JOIN line_items ON order_id=orders.id", options.getAs());
    }



    @Test
    @DisplayName("可以设置主键类型")
    void couldSetIdType() {
        options.setIdType("string");
        assertEquals("string", options.getIdType());
    }
}