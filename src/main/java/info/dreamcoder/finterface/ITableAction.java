package info.dreamcoder.finterface;

import info.dreamcoder.table.ColumnMethod;

@FunctionalInterface
public interface ITableAction {

    void run(ColumnMethod columnMethod);

}
