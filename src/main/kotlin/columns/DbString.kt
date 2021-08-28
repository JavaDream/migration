package columns

class DbString(name: String) : Column(name) {
    override fun toSql(): String {
        return "$name varchar(250)"
    }
}