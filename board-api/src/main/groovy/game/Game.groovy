package game

import game.entitys.Entity
import game.event.EventBus
import game.systems.ECSSystem

/**
 * Created by tejp on 16/07/15.
 */
class Game {
    EventBus eventBus = new EventBus()
    Set<Entity> entitys = new HashSet<>()
    Set<ECSSystem> systems = new HashSet<>()

}
