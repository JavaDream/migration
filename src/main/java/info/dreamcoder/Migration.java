package info.dreamcoder;

import info.dreamcoder.finterface.ITableAction;
import info.dreamcoder.jdbc.Database;

import java.sql.SQLException;


/**
 * Hello world!
 *
 */
public class Migration  {

    public static Table createTable(String name, ITableAction run) throws SQLException {
        Table table = new Table(name);
        run.run(table);
        makeChangeToDatabase(table);
        return table;
    }

    public static Table createTable(String name, TableOptions options, ITableAction run) throws SQLException {
        Table table = new Table(name, options);
        run.run(table);
        makeChangeToDatabase(table);
        return table;
    }

    private static void makeChangeToDatabase(Table table) throws SQLException {
        Database database = new Database(
                System.getenv("MYSQL_URL"),
                System.getenv("MYSQL_USERNAME"),
                System.getenv("MYSQL_PASSWORD")
        );
        database.execute(table.toSql());
    }

    private Migration() {
        throw new IllegalStateException("Utility class");
    }
}
