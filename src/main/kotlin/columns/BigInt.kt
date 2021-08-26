package columns

class BigInt(name: String) : Column(name) {
    override fun toSql(): String {
       return "$name bigint"
    }
}
