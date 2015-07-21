package game

/**
 * Created by tejp on 16/07/15.
 */
abstract class Action {

    Game game

    Action (Game game) {
        this.game = game
    }

    abstract void execute()
}