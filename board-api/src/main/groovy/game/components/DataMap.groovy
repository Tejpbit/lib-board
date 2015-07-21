package game.components

/**
 * Created by tejp on 17/07/15.
 */
class DataMap<K,V> implements Component{

    String mapname
    Map<K,V> map

    DataMap(String mapname, Map<K, V> map) {
        this.mapname = mapname
        this.map = map
    }

    DataMap(String mapname) {
        this.mapname = mapname
        this.map = new HashMap()
    }
}
