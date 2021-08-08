package info.dreamcoder.columns;

public abstract class DBColumn {

    private String name;
    private String options = "";

    public String getName() {
        return name;
    }


    protected DBColumn(String name) {
        this.name = name;
    }

    public String toSql() {
        StringBuilder sqlBuilder = new StringBuilder(this.sql());
        sqlBuilder.append(" ").append(this.options);
        return sqlBuilder.toString().strip();
    }

    protected abstract String sql();

    public void addOptions(String options) {
        this.options = options;
    }
}

