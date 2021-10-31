package gradle

import org.gradle.api.Project
import java.nio.file.Paths
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MigrationConfig(private val project: Project) {

    companion object {
        private var mConfig: MigrationConfig? = null
        val config: MigrationConfig
            get() {
                return mConfig ?: throw AssertionError("config 需要初始化")
            }

        fun create(project: Project) {
            mConfig = MigrationConfig(project)
        }
    }

    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
    private val migrationDir = "src/main/kotlin/migrations"

    fun migrationPath(path: String = ""): String = Paths.get(
        project.rootDir.path,
        migrationDir,
        path
    ).toString()

    fun parseDate(date: String): LocalDateTime = LocalDateTime.parse(date, dateFormatter)
    fun dateFormat(date: LocalDateTime): String = date.format(dateFormatter)

}