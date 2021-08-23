package info.dreamcoder.table;

import info.dreamcoder.columns.*;

import java.util.ArrayList;
import java.util.List;

public class ColumnMethod {
    private List<DBColumn> columns = new ArrayList<>();

    public DBString string(String name) {
        DBString column = new DBString(name);
        columns.add(column);
        return column;
    }

    public void integer(String name) {
        DBInteger column = new DBInteger(name);
        columns.add(column);
    }


    public DBBigInt bigint(String name) {
        DBBigInt column = new DBBigInt(name);
        columns.add(column);
        return column;
    }

    public void timestamps() {
        DBTimestamp createdAt = new DBTimestamp("created_at");
        DBTimestamp updatedAt = new DBTimestamp("updated_at");
        columns.add(createdAt);
        columns.add(updatedAt);
    }

    protected String toSql() {
        StringBuilder sql = new StringBuilder();
        for (int i = 0; i < columns.size(); i++) {
            sql.append(columns.get(i).toSql());
            if(i < columns.size() - 1) {
                sql.append(",\n");
            }
        }
        sql.append("\n)");
        return sql.toString();
    }
}
