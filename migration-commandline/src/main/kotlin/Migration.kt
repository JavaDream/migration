import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.time.format.DateTimeFormatter

class Migration : DefaultTask() {
    companion object {
        const val path = "/tmp/migrations" //未来要改成项目目录
        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("YYYYMMDDHHMMSS")
    }

    @TaskAction
    fun run() = Unit
}

