package game.components
/**
 * Created by tejp on 16/07/15.
 */
class DataTable<T> implements Component{

    DataTable(String tableName, T[] data) {
        this.tableName = tableName
        this.data = data
    }

    String tableName
    T[] data
}
