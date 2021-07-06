package info.dreamcoder;


import info.dreamcoder.columns.DBColumn;
import info.dreamcoder.columns.DBString;

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

//    TODO:
//     1. 添加integer方法
//     2. 添加 timestamps方法

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
