package columns

class DbInt(name: String) : Column(name) {
    override fun toSql(): String {
        return "$name int"
    }
}