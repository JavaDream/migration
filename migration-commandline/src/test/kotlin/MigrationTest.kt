import org.amshove.kluent.shouldNotBeNull
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class MigrationTest {

    private val pluginId = "info.dreamcoder.migration"

    @Test
    @DisplayName("能正确的注册插件之后")
    fun shouldHasPluginWhenProjectRegisterPlugin() {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply(pluginId)
        project.pluginManager.hasPlugin(pluginId)
    }


    @Test
    @DisplayName("注册了插件之后，就会有 gradle migration.create 命令")
    fun shouldCreateMigrationCreateTask() {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply(pluginId)
        project.tasks.getByName("migration.create").shouldNotBeNull()
    }

}
