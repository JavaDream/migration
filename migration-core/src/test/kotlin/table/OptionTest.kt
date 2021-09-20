package table

import columns.ColumnType
import org.amshove.kluent.*
import org.junit.jupiter.api.*

class OptionTest {
    lateinit var option: Option

    @BeforeEach
    fun initOption() {
        option = Option()
    }

    @Nested
    @DisplayName("id配置")
    inner class IdOptionTest {

        @Test
        @DisplayName("默认值是true")
        fun defaultValueShouldBeTrue() {
            option.needId.shouldBeTrue()
        }

        @Test
        @DisplayName("能关闭id")
        fun couldDisableId() {
            option id false
            option.needId.shouldBeFalse()
        }

        @Test
        @DisplayName("能正确设置id列的名字")
        fun couldSetIdColumnName() {
            option id ColumnType.String

            option.idType shouldBeEqualTo ColumnType.String
        }
    }

    @Nested
    @DisplayName("primaryKey配置")
    inner class PrimaryKeyOptionTest() {

        @Test
        @DisplayName("默认名字是id")
        fun defaultNameIsId() {
            option.primaryKey shouldBeEqualTo "id"
        }

        @Test
        @DisplayName("能正确设置名字")
        fun couldSetName() {
            option primaryKey "test_key"
            option.primaryKey shouldBeEqualTo "test_key"
        }
    }

    @Nested
    @DisplayName("options配置")
    inner class OptionsTest() {

        @Test
        @DisplayName("默认值是空字符串")
        fun defaultValueIsBlankString() {
            option.options.shouldBeEmpty()
        }

        @Test
        @DisplayName("能正确配置")
        fun couldSetValue() {
            val options = "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4"
            option options options
            option.options shouldBeEqualTo options
        }
    }

    @Nested
    @DisplayName("force配置")
    inner class ForceOptionTest() {

        @Test
        @DisplayName("默认值是false")
        fun defaultValueIsFalse() {
            option.force shouldBeEqualTo false
        }

        @Test
        @DisplayName("能正确配置")
        fun couldSetValue() {
            option force true
            option.force shouldBeEqualTo true
        }
    }

    @Nested
    @DisplayName("asSql配置")
    inner class AsSqlOptionTest() {

        @Test
        @DisplayName("默认值是空字符串")
        fun defaultValueIsFalse() {
            option.asSql.shouldBeEmpty()
        }

        @Test
        @DisplayName("能正确配置")
        fun couldSetValue() {
            val sql = "SELECT * FROM orders INNER JOIN line_items ON order_id=orders.id"
            option asSql sql
            option.asSql shouldBeEqualTo sql
        }
    }



}
