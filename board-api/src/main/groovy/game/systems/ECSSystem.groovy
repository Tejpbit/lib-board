package game.systems

import game.Game
import game.event.EventListener

/**
 * Created by tejp on 16/07/15.
 */
abstract class ECSSystem {

    Game game
    Set<EventListener> listeners
    ECSSystem (Game game) {
        this.game = game
    }

    abstract void doAction()

}