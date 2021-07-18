package info.dreamcoder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("测试migration类")
class MigrationTest {

    @Test
    @DisplayName("调用构造函数的时候应该抛出异常")
    void shouldThrowExceptionWhenNewTheClass() throws ClassNotFoundException {
        Class<?> migrationClass = Class.forName(Migration.class.getName());

        assertThrows(IllegalAccessException.class, () -> {
            migrationClass.getDeclaredConstructor().newInstance();
        });
    }

    @Test
    @DisplayName("静态方法应该得到一个Table对象")
    void shouldGetATableObject() {
        Table table = Migration.createTable("test_table", t -> {});
        assertEquals("info.dreamcoder.Table", table.getClass().getName());
    }
}