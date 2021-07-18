package info.dreamcoder.columns;

public abstract class DBColumn {

    private String name;

    public String getName() {
        return name;
    }


    protected DBColumn(String name) {
        this.name = name;
    }

    public abstract String toSql();
}
