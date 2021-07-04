package info.dreamcoder;

/**
 * Hello world!
 *
 */
public class Migration  {


    public static void createTable(String name, ITableRun run) {
        Table table = new Table(name);
        run.action(table);
        System.out.println(table.toSql());
    }

    public static void main( String[] args ) {
        createTable("test_table", (t) -> {
            t.string("test_string_column");
        });
    }
}
