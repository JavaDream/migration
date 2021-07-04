package info.dreamcoder;


import java.util.ArrayList;
import java.util.List;

public class Table {
    private String name;
    private List<DBColumn> columns = new ArrayList<DBColumn>();

    public Table(String name) {
        this.name = name;
    }

    public void string(String name) {
        DBString column = new DBString(name);
        columns.add(column);
    }

    public String toSql() {
        String sql = "CREATE TABLE " + this.name + "\n";
        sql += "(\n";
        for (DBColumn column : columns) {
            sql += column.toSql();
        }
        sql += "\n)";
        return sql;
    }
}
