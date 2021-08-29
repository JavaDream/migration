package columns

abstract class Column(val name: String) {
    internal abstract fun toSql(): String
}

enum class ColumnType {
    BigInt,
    Int,
    String,
}
