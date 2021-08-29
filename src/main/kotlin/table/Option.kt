package table

import columns.ColumnType

class Option {
    internal var needId = true
        private set

    internal var idType = ColumnType.BigInt
        private set

    internal var primaryKey = "id"
        private set

    internal var options = ""
        private set

    internal var force = false
        private set

    internal var asSql = ""
        private set

    infix fun id(needId: Boolean): Option {
        this.needId = needId
        return this
    }

    infix fun id(idType: ColumnType): Option {
        this.idType = idType
        return this
    }

    infix fun primaryKey(name: String): Option {
        primaryKey = name
        return this
    }

    infix fun options(options: String): Option {
        this.options = options
        return this
    }

    infix fun force(force: Boolean): Option {
        this.force = force
        return this
    }

    infix fun asSql(sql: String): Option {
        this.asSql = sql
        return this
    }


}