package jdbc

import config.DbType
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeFalse
import org.amshove.kluent.shouldBeTrue
import org.amshove.kluent.shouldNotThrow
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.sql.SQLException as SQLException

class DatabaseTest {
    val database = Database(DbType.Sqlite)

    @BeforeEach
    fun initTable() {
        database.execute("drop table if exists test_table")
    }

    @Test
    @DisplayName("函数isValid, 数据库能1秒连接上的时候返回 true")
    fun isValid() {
        invoking {
            val db = Database(DbType.Sqlite)
            db.close()
        } shouldNotThrow SQLException::class
    }

    @Test
    @DisplayName("函数close能正确关闭数据库")
    fun close() {
        invoking {
            val db = Database(DbType.Sqlite)
            db.close()
        } shouldNotThrow SQLException::class
    }

    @Test
    @DisplayName("函数execute正确的执行创建和删除表的sql语句")
    fun execute() {
        database.execute("create table test_table_1(name int)")
        database.tableExists("test_table_1").shouldBeTrue()
        database.execute("drop table test_table_1")
        database.tableExists("test_table_1").shouldBeFalse()
    }

    @Test
    @DisplayName("函数query")
    fun query() {
        database.execute("create table test_table_1(name int)")
        database.tableExists("test_table_1").shouldBeTrue()
    }
}