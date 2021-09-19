import jdbc.Database
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class CreatePluginTest {

    @Test
    fun createFile() {
        clearMigrations()
        CreatePlugin().createFile()
        assertTrue(
            File(Migration.path).walk().any {
                Regex(".*\\.kt").matches(it.name)
            }
        )
    }

    @Test
    fun createMigrationTable() {
        CreatePlugin().createMigrationTable()

        assertTrue(
            Database.instance.tableExists("migrations")
        )
    }

    @Test
    fun shouldCreateMigrationCreateTask() {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply("info.dreamcoder.migration")
        assertNotNull( project.tasks.getByName("migration.create") )
    }


    private fun clearMigrations() {
        val dir = File(Migration.path)
        dir.deleteRecursively()
    }
}