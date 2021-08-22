package info.dreamcoder.columns;

public class DBBigInt extends DBColumn {
    private boolean autoIncrement = false;

    public DBBigInt(String name) {
        super(name);
    }

    @Override
    public String sql() {
        String sql = this.getName() + " bigint";
        if (autoIncrement) {
            sql += " auto_increment";
        }
        return sql;
    }

    public DBBigInt autoIncrement() {
        this.autoIncrement = true;
        return this;
    }
}
