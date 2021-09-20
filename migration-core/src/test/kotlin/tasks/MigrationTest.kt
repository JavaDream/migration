package tasks

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class MigrationTest {

    @Test
    fun version() {

        M.version("202109248120969") {
            up {
                createTable("test_table") {
                    column string "name"
                }
            }
        }

        M.tasks.size shouldBeEqualTo 1
    }
}