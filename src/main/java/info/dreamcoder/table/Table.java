package info.dreamcoder.table;

public class Table {
    private String name;
    ColumnMethod columnMethod = new ColumnMethod();
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
                    columnMethod.bigint(options.getPrimaryKey()).autoIncrement().primary();
                    break;
                case "string":
                    columnMethod.string(options.getPrimaryKey()).primary();
                    break;
                default: break;
            }
        }
    }

    public ColumnMethod columnMethod() {
        return this.columnMethod;
    }

    public String toSql() {
        StringBuilder sql = new StringBuilder();
        if(options.getAs().isEmpty()) {
            sql.append("CREATE TABLE ").append(this.name).append("\n");
            sql.append("(\n");

            sql.append(columnMethod.toSql());

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
