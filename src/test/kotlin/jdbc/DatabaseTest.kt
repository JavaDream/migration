package jdbc

import config.DbConfig
import config.DbType
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import org.assertj.db.type.Request
import org.assertj.db.type.Source
import java.sql.SQLException

@DisplayName("jdbc/Database类")
class DatabaseTest : DescribeSpec({

    val database = Database(DbType.Sqlite)
    val dbSource = Source(DbConfig(DbType.Sqlite).dbUrl, "", "")

    beforeEach {
        database.execute("drop table if exists test_table")
    }

    describe("函数isValid") {
        it("数据库能1秒连接上的时候返回 true") {
            database.isValid().shouldBeTrue()
        }
    }

    describe("函数close") {
        it("能正确的关闭数据库") {
            shouldNotThrow<SQLException> {
                val db = Database(DbType.Sqlite)
                db.close()
            }
        }
    }

    describe("函数execute") {
        it("正确的执行创建和删除表的sql语句") {
            database.execute("create table test_table_1(name int)")
            var request = Request(dbSource, "select count(*) from sqlite_master where type='table' and name='test_table_1'")
            request.getRow(0).getColumnValue(0).value shouldBe 1
            database.execute("drop table test_table_1")
            request = Request(dbSource, "select count(*) from sqlite_master where type='table' and name='test_table_1'")
            request.getRow(0).getColumnValue(0).value shouldBe 0
        }
    }

    describe("函数query") {
        database.execute("create table test_table_1(name int)")
        val rs = database.query("select count(*) from sqlite_master where type='table' and name='test_table_1'")
        rs.next()
        rs.getInt(1) shouldBe 1
    }
})
