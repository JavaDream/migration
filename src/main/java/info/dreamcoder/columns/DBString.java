package info.dreamcoder.columns;

public class DBString extends DBColumn {

    public DBString(String name) {
        super(name);
    }

    @Override
    public String toSql() {
        return this.getName() + " varchar(255)";
    }
}
