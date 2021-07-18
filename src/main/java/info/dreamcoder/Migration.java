package info.dreamcoder;

import info.dreamcoder.finterface.ITableAction;

/**
 * Hello world!
 *
 */
public class Migration  {

    public static Table createTable(String name, ITableAction run) {
        Table table = new Table(name);
        run.run(table);
        return table;
    }

    private Migration() {
        throw new IllegalStateException("Utility class");
    }
}
