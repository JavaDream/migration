package tasks

import jdbc.Database
import org.amshove.kluent.*
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.tooling.GradleConnector
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import java.io.File

internal class CreateTaskTest {
    private val testProjectPath = "/Users/jimxl/project/java/migration/test_project"

    private val project: Project = ProjectBuilder
        .builder()
        .withProjectDir(File(testProjectPath))
        .build()

    private lateinit var task: CreateTask

    @BeforeEach
    fun initTest() {
        task = project.tasks.create("migration.create", CreateTask::class.java)
        task.create()
    }

    @AfterEach
    fun clear() {
//        clearMigrations()
    }

    @Test
    @DisplayName("CreateTask任务能正确的创建迁移文件")
    fun shouldCreateMigrationFileWhenTaskRun() {
        val files = File(task.config.migrationPath()).list()

        files.shouldNotBeNull()
        files.size shouldBeEqualTo 1

        Regex(".*\\.kt").matches(files[0]).shouldBeTrue()
    }

    @Test
    @DisplayName("CreateTask任务能正确迁移记录数据库")
    fun shouldCreateMigrationDatabaseWhenTaskRun() {
        Database.instance.tableExists("migrations").shouldBeTrue()
    }

    private fun clearMigrations() {
        val dir = File(task.config.migrationPath())
        dir.deleteRecursively()
    }
}

private class Delegate() {

}