package gradle

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeTrue
import org.amshove.kluent.shouldMatch
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MigrationVersionTest {

    @Test
    fun `能正确的得到migration的文件名`() {
        val project = ProjectBuilder.builder().build()
        MigrationConfig.create(project)
        val version = MigrationVersion("create_table_test")

        val regex = Regex("${MigrationConfig.config.migrationPath()}/CreateTableTest_\\d+")

        assertTrue(regex.containsMatchIn(version.filePath()))
    }
}