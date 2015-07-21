package game.components

import game.entitys.Entity

/**
 * Created by tejp on 16/07/15.
 */
class Tile implements Component {
    Set<Entity> successors = new HashSet<>()
    Set<Entity> predecessors = new HashSet<>()
}
