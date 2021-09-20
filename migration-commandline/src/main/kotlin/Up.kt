//import com.github.ajalt.clikt.core.CliktCommand
//import jdbc.Database
//import java.io.File
//import java.time.LocalDateTime
//
//class Up : CliktCommand(help="创建迁移脚本") {
//    override fun run() {
//    }
//
//    private fun versionList() : List<String> {
//        return File(Migration.path)
//            .walk()
//            .filter { it.extension == "kt" }
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
//
//    private fun updateVersion() {
//
//    }
//
//    private fun compareVersion(version1: String, version2: String): Boolean {
//        val date1 = LocalDateTime.parse(version1, Migration.dateFormatter)
//        val date2 = LocalDateTime.parse(version2, Migration.dateFormatter)
//        return date1 > date2
//    }
//}
