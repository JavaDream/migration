package info.dreamcoder.columns;

public class DBInteger extends DBColumn {
    private boolean autoIncrement = false;

    public DBInteger(String name) {
        super(name);
    }

    @Override
    public String sql() {
        String sql = this.getName() + " int";
        if (autoIncrement) {
            sql += " auto_increment";
        }
        return sql;
    }

    public DBInteger autoIncrement() {
        this.autoIncrement = true;
        return this;
    }
}
