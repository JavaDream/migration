package tasks

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MigrationTest : FunSpec({

    test("version") {
        M.version("202109248120969") {
            up {
                createTable("test_table") {
                    column string "name"
                }
            }
        }

        M.tasks.size shouldBe 1
    }
})
