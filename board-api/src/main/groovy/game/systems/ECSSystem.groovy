package game.systems

import game.Game
import game.event.*

/**
 * Created by tejp on 16/07/15.
 */
abstract class ECSSystem implements EventListener {

    Game game
    ECSSystem (Game game) {
        this.game = game
        game.eventBus.addListener(this)
    }

    abstract void doAction()
}