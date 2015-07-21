package game.systems

import game.Game

/**
 * Created by tejp on 16/07/15.
 */
abstract class ECSSystem {

    Game game
    ECSSystem (Game game) {
        this.game = game
    }

    abstract void doAction()
}