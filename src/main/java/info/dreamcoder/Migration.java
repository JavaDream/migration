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

//    TODO: 添加数据库连接以及真实的创建表的方法
}
