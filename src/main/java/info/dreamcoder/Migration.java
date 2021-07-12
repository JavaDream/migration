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

//    TODO: 添加数据库连接以及真实的创建表的方法
}
