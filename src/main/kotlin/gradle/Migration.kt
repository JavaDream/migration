package gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import gradle.tasks.CreateTask

class Migration : Plugin<Project> {

    override fun apply(target: Project) {
        initConfig(target)
        initDatabaseDriver()

        registerTask(target, "migration.create", CreateTask::class.java)
    }

    private fun initConfig(project: Project) {
        MigrationConfig.create(project)
    }


    //  由于Gradle不能自动加载数据库的驱动，所以这儿要手动加载, 暂时先加载sqlite的，未来还要加载mysql等各种支持的数据库
    private fun initDatabaseDriver() {
        Class.forName("org.sqlite.JDBC")
    }

    private fun <T : Task> registerTask(project: Project, name: String, type: Class<T>) {
        project.tasks.create(name, type) {
            it.group = "dreamcoder"
        }
    }
}

