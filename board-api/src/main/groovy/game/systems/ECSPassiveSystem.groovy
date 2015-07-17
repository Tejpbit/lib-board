package game.systems

import game.Game
import game.event.EventListener

/**
 * Created by tejp on 16/07/15.
 */
abstract class ECSPassiveSystem implements EventListener {

    Game game

    ECSPassiveSystem(Game game) {
        this.game = game
        game.eventBus.addListener(this)
    }
}