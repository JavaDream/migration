package info.dreamcoder;

import info.dreamcoder.columns.*;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private String name;
    private List<DBColumn> columns = new ArrayList<>();
    private TableOptions options;

    public Table(String name) {
        this(name, new TableOptions());
    }

    public Table(String name, TableOptions options) {
        this.name = name;
        this.options = options;

        // 添加主键id
        // 这个地方还需要支持 多个ID作为主键的情况

        if(options.isNeedId()) {
            switch (options.getIdType()) {
                case "bigint":
                    this.bigint(options.getPrimaryKey(), "auto_increment PRIMARY KEY");
                    break;
                case "string":
                    this.string(options.getPrimaryKey(), "PRIMARY KEY");
                    break;
                default: break;
            }

        }
    }

    public void string(String name) {
        DBString column = new DBString(name);
        columns.add(column);
    }

    public void string(String name, String options) {
        DBString column = new DBString(name);
        column.addOptions(options);
        columns.add(column);
    }

    public void integer(String name) {
        DBInteger column = new DBInteger(name);
        columns.add(column);
    }

    public void integer(String name, String options) {
        DBInteger column = new DBInteger(name);
        column.addOptions(options);
        columns.add(column);
    }

    public void bigint(String name) {
        DBBigInt column = new DBBigInt(name);
        columns.add(column);
    }

    public void bigint(String name, String options) {
        DBBigInt column = new DBBigInt(name);
        column.addOptions(options);
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
        if(options.getAs().isEmpty()) {
            sql.append("CREATE TABLE ").append(this.name).append("\n");
            sql.append("(\n");

            for (int i = 0; i < columns.size(); i++) {
                sql.append(columns.get(i).toSql());
                if(i < columns.size() - 1) {
                    sql.append(",\n");
                }
            }
            sql.append("\n)");

            // 添加数据库的options 例如 ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 这样的参数
            if(!options.getOptions().isEmpty()) {
                sql.append(options.getOptions());
            }
        } else {
            sql.append("CREATE TABLE ").append(this.name).append(" AS\n");
            sql.append(options.getAs());
        }
        return sql.toString();
    }
}
