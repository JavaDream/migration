package info.dreamcoder.columns;

public class DBBigInt extends DBColumn {

    public DBBigInt(String name) {
        super(name);
    }

    @Override
    public String sql() {
        return this.getName() + " bigint";
    }
}
