package columns

class DbBigInt(name: String) : Column(name) {
    override fun toSql(): String {
       return "$name bigint"
    }
}
