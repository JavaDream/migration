package gradle.tasks
//
//import gradle.MigrationConfig
//import jdbc.Database
//import org.gradle.api.tasks.Input
//import org.gradle.api.tasks.TaskAction
//import java.io.File
//
//class UpTask : BaseTask() {
//
//    @Input
//    val migrationName = "latest"
//
//    @TaskAction
//    fun execute() {
//        TODO("编写内容")
//    }
//
//    private fun versionList(): List<String> {
//        return File(MigrationConfig.config.migrationPath())
//            .walk()
//            .filter { it.extension == "kt" }
//            .sorted()
//            .map { it.name }.toList()
//    }
//
//    private fun currentVersion(): String {
//        val migrations = Database.instance.query(
//            "select top 1 * from migrations order by id desc")
//        if(migrations.next()) {
//            return migrations.getString("name").split("_").first()
//        }
//        return ""
//    }
//}
