import org.amshove.kluent.shouldNotBeNull
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Test

internal class MigrationTest {

    @Test
    fun shouldCreateMigrationCreateTask() {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply("info.dreamcoder.migration")
        project.tasks.getByName("migration.create").shouldNotBeNull()
    }
}