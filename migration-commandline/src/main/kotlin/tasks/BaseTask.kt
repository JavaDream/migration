package tasks

import MigrationConfig
import org.gradle.api.DefaultTask

abstract class BaseTask : DefaultTask() {
    internal val config = MigrationConfig(project)
}
