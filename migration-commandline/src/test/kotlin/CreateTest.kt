import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.sequences.shouldExist
import io.kotest.matchers.shouldBe
import java.io.File

class CreateTest : FunSpec({

    test("run方法能正确的生成文件") {
        Create().parse(listOf())
        File(Migration.path).walk().shouldExist {
            Regex(".*\\.kt").matches(it.name)
        }
    }
})
