package columns

abstract class Column(val name: String) {
    internal abstract fun toSql(): String
}
