package columns

class DbString(name: String) : Column(name) {
    private var length = 250

    override fun toSql(): String {
        return "$name varchar($length)"
    }

    infix fun limit(length: Int): DbString {
        this.length = length
        return this
    }
}