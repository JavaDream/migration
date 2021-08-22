package info.dreamcoder.columns;

public class DBString extends DBColumn {
    private int length = 255;

    public DBString(String name) {
        super(name);
    }

    @Override
    public String sql() {
        return this.getName() + " varchar(" + length + ")";
    }

    public DBString limit(int length) {
        this.length = length;
        return this;
    }
}
