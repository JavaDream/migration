import org.gradle.api.Plugin
import org.gradle.api.Project
import tasks.CreateTask

class Migration : Plugin<Project> {

    override fun apply(target: Project) {
        target.tasks.create("migration.create", CreateTask::class.java) {
            it.group = "dreamcoder"
        }
    }
}

