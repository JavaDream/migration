package info.dreamcoder;

public class DBInteger extends DBColumn {
    public DBInteger(String name) {
        super(name);
    }

    @Override
    public String toSql() {
        String sql = this.getName() + " int";
        return sql;
    }
}
