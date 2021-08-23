package info.dreamcoder;

import info.dreamcoder.table.Table;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

@DisplayName("测试migration类")
class MigrationTest {

    @Test
    @DisplayName("构造函数是私有的")
    void shouldHaveAPrivateConstructorMethod() throws NoSuchMethodException {
        Constructor<Migration> constructor = Migration.class.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();
    }

    @Test
    @DisplayName("存在构造函数")
    void shouldHaveConstructorMethod() {
        assertThatNoException().isThrownBy(() -> {
            Constructor<Migration> constructor = Migration.class.getDeclaredConstructor();
        });
    }

    @Test
    @DisplayName("调用构造函数的时候后抛出异常")
    void shouldThrowExceptionWhenNewTheClass() throws NoSuchMethodException {
        Constructor<Migration> constructor = Migration.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        assertThatExceptionOfType(InvocationTargetException.class).isThrownBy(constructor::newInstance);

        constructor.setAccessible(false);
    }

    @Test
    @DisplayName("调用构造函数的时候后抛出异常类型为IllegalStateException")
    void shouldThrowIllegalStateExceptionExceptionWhenNewTheClass() throws NoSuchMethodException, InstantiationException, IllegalAccessException {
        Constructor<Migration> constructor = Migration.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        try {
            constructor.newInstance();
        } catch (InvocationTargetException e) {
            assertThat(e).getCause().isInstanceOf(IllegalStateException.class);
        }

        constructor.setAccessible(false);
    }

    @Test
    @DisplayName("静态方法应该得到一个Table对象")
    void shouldGetATableObject() throws SQLException {
        Table table = Migration.createTable("test_table", t -> {});

        assertThat(table.getClass().getName()).isEqualTo("info.dreamcoder.table.Table");
    }
}