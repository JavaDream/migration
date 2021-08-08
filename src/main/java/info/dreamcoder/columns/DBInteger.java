package info.dreamcoder.columns;

public class DBInteger extends DBColumn {
    public DBInteger(String name) {
        super(name);
    }

    @Override
    public String sql() {
        return this.getName() + " int";
    }
}
