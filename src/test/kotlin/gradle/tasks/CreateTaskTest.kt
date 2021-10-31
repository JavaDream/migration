package gradle.tasks

import gradle.MigrationConfig
import jdbc.Database
import org.amshove.kluent.*
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import java.io.File

internal class CreateTaskTest {

    private val project: Project = ProjectBuilder
        .builder()
//        .withProjectDir(File(testProjectPath))
        .build().also {
            it.pluginManager.apply("info.dreamcoder.migration")
        }

    private var task: CreateTask = project.tasks.getByName("migration.create") as CreateTask

    @BeforeEach
    fun initTest() {
        task.create()
    }

    @AfterEach
    fun clear() {
        clearMigrations()
    }

    @Test
    @DisplayName("CreateTask任务能正确的创建迁移文件")
    fun shouldCreateMigrationFileWhenTaskRun() {
        val files = File(MigrationConfig.config.migrationPath()).list()

        files.shouldNotBeNull()
        files.size shouldBeEqualTo 1

        Regex(".*\\.kt").matches(files[0]).shouldBeTrue()
    }

    @Test
    @DisplayName("CreateTask任务能正确迁移记录数据库")
    fun shouldCreateMigrationDatabaseWhenTaskRun() {
        Database.instance.tableExists("migrations").shouldBeTrue()
    }

    @Test
    @DisplayName("CreateTask任务执行后，migrations的文件内容包含package名")
    fun migrationFileShouldContainsPackageNameWhenTaskRun() {
        val files = File(MigrationConfig.config.migrationPath()).listFiles()

        File(files[0].absoluteFile.toString()).readText() shouldContain "package migrations"
    }

    private fun clearMigrations() {
        val dir = File(MigrationConfig.config.migrationPath())
        dir.deleteRecursively()
    }
}

private class Delegate() {

}