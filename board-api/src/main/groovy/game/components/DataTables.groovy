package game.components

import game.components.Component

import java.awt.*

/**
 * Created by tejp on 16/07/15.
 */
class DataTables<T> implements Component{

    DataTables(Set<DataTable<T>> tables) {
        this.tables = tables
    }

    DataTables() {
        table = new HashSet<>()
    }


    Set<DataTable<T>> tables

}
