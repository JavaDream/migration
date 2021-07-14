package info.dreamcoder.columns;

public class DBTimestamp extends DBColumn{
    public DBTimestamp(String name) {
        super(name);
    }

    @Override
    public String toSql() {
        String sql = this.getName() + " timestamp";
        return sql;
    }
}