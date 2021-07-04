package info.dreamcoder;

public class DBString extends DBColumn {

    public DBString(String name) {
        super(name);
    }

    @Override
    public String toSql() {
        String sql = this.getName() + " varchar(255)";
        return sql;
    }
}
