package info.dreamcoder;

/**
 * Hello world!
 *
 */
public class Migration  {


    public static Table createTable(String name, ITableRun run) {
        Table table = new Table(name);
        run.action(table);
        return table;
    }

    public static void main( String[] args ) {
        createTable("test_table", (t) -> {
            t.string("test_string_column");
        });
    }
}
