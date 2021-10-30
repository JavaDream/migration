import java.io.File
import java.time.LocalDateTime

class MigrationVersion {
    private lateinit var versionNumber: String
    private lateinit var name: String

    constructor(name: String) {
        this.name = name.camelize()
        versionNumber = now()
    }

    constructor(file: File) {
        var info = file.nameWithoutExtension.split("_")
        name = info[1]
        versionNumber = info[0]

    }

    private fun fileName() = "${name}_${versionNumber}.kt"

    fun filePath(): String {
        val file = File(MigrationConfig.config.migrationPath())
        file.mkdir()
        return MigrationConfig.config.migrationPath(fileName())
    }


    private fun now(): String  = MigrationConfig.config.dateFormat(LocalDateTime.now())
}
