package gradle

import camelize
import java.io.File
import java.time.LocalDateTime

class MigrationVersion {
    private var versionNumber: String
    private var name: String

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
        val dir = File(MigrationConfig.config.migrationPath())
        dir.mkdirs()
        return MigrationConfig.config.migrationPath(fileName())
    }


    private fun now(): String  = MigrationConfig.config.dateFormat(LocalDateTime.now())
}
