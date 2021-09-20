import jdbc.Database
import org.amshove.kluent.shouldBeTrue
import org.amshove.kluent.shouldNotBeNull
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Test

import java.io.File

internal class CreatePluginTest {

    @Test
    fun createFile() {
        clearMigrations()
        CreatePlugin().createFile()

        File(Migration.path).walk().any {
            Regex(".*\\.kt").matches(it.name)
        }.shouldBeTrue()

    }

    @Test
    fun createMigrationTable() {
        CreatePlugin().createMigrationTable()

        Database.instance.tableExists("migrations").shouldBeTrue()
    }

    @Test
    fun shouldCreateMigrationCreateTask() {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply("info.dreamcoder.migration")
        project.tasks.getByName("migration.create").shouldNotBeNull()
    }


    private fun clearMigrations() {
        val dir = File(Migration.path)
        dir.deleteRecursively()
    }
}