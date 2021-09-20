import java.time.format.DateTimeFormatter

object MigrationConfig {
    const val path = "/tmp/migrations" //未来要改成项目目录
    val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("YYYYMMDDHHMMSS")
}