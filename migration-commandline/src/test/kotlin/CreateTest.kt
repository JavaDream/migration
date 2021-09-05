import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.sequences.shouldExist
import io.kotest.matchers.shouldBe
import jdbc.Database
import java.io.File

class CreateTest : FunSpec({
    test("run方法能正确的生成文件") {
        clearMigrations()
        Create().parse(listOf())
        File(Migration.path).walk().shouldExist {
            Regex(".*\\.kt").matches(it.name)
        }

        Database.instance.tableExists("migrations").shouldBeTrue()
    }
})

fun clearMigrations() {
    val dir = File(Migration.path)
    dir.deleteRecursively()
}

