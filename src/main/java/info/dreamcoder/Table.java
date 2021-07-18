package info.dreamcoder;


import info.dreamcoder.columns.DBColumn;
import info.dreamcoder.columns.DBInteger;
import info.dreamcoder.columns.DBString;
import info.dreamcoder.columns.DBTimestamp;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private String name;
    private List<DBColumn> columns = new ArrayList<>();

    public Table(String name) {
        this.name = name;
    }

    public void string(String name) {
        DBString column = new DBString(name);
        columns.add(column);
    }

    public void integer(String name) {
        DBInteger column = new DBInteger(name);
        columns.add(column);
    }

    public void timestamps() {
        DBTimestamp createdAt = new DBTimestamp("created_at");
        DBTimestamp updatedAt = new DBTimestamp("updated_at");
        columns.add(createdAt);
        columns.add(updatedAt);
    }

    public String toSql() {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE ").append(this.name).append("\n");
        sql.append("(\n");
        for (DBColumn column : columns) {
            sql.append(column.toSql());
        }
        sql.append("\n)");
        return sql.toString();
    }
}
