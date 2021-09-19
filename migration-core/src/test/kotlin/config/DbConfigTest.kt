package config

import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.spec.style.Test
import io.kotest.matchers.shouldBe

@DisplayName("DbConfig类")
class DbConfigTest : DescribeSpec({

    describe("dbUrl") {
        it("当使用mysql的时候，能准确从环境变量读取") {
            val config = DbConfig(DbType.Mysql)
            config.dbUrl shouldBe System.getenv("MYSQL_URL")
        }
    }
})
