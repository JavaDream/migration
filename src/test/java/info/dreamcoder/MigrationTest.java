package info.dreamcoder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("测试migration类")
class MigrationTest {

    @Test
    @DisplayName("构造函数是私有的")
    void shouldHaveAPrivateConstructorMethod() throws NoSuchMethodException {
        Constructor<Migration> constructor = Migration.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    }

    @Test
    @DisplayName("存在构造函数")
    void shouldHaveConstructorMethod() {
        assertDoesNotThrow(() -> {
            Constructor<Migration> constructor = Migration.class.getDeclaredConstructor();
        });
    }

    @Test
    @DisplayName("调用构造函数的时候后抛出异常")
    void shouldThrowExceptionWhenNewTheClass() throws NoSuchMethodException {
        Constructor<Migration> constructor = Migration.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        assertThrows(InvocationTargetException.class, constructor::newInstance);

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
            assertTrue(e.getTargetException() instanceof IllegalStateException);
        }

        constructor.setAccessible(false);
    }

    @Test
    @DisplayName("静态方法应该得到一个Table对象")
    void shouldGetATableObject() {
        Table table = Migration.createTable("test_table", t -> {});
        assertEquals("info.dreamcoder.Table", table.getClass().getName());
    }
}