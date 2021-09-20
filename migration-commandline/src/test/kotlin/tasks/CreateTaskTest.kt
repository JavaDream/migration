package tasks

import jdbc.Database
import org.amshove.kluent.*
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import java.io.File

internal class CreateTaskTest {

    @Test
    @DisplayName("CreateTask任务能正确的创建迁移文件和迁移记录数据库")
    fun run() {
        clearMigrations()
        val project = ProjectBuilder.builder().build()
        val task = project.tasks.create("migration.create", CreateTask::class.java)

        task shouldBeInstanceOf CreateTask::class

        task.run()

        val files = File(MigrationConfig.path).list()

        files.shouldNotBeNull()
        files.size shouldBeEqualTo 1

        Regex(".*\\.kt").matches(files[0]).shouldBeTrue()

        Database.instance.tableExists("migrations").shouldBeTrue()
    }

    private fun clearMigrations() {
        val dir = File(MigrationConfig.path)
        dir.deleteRecursively()
    }
}