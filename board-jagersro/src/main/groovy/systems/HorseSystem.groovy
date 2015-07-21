package systems

import game.Game
import game.event.Event
import game.event.EventListener

import game.systems.ECSSystem

/**
 * Created by tejp on 16/07/15.
 */
class HorseSystem extends ECSSystem implements EventListener<Event> { //Todo create horse event thingy. maybe climp together all buy/sell things in one event and one system? could be hard to hold the buyables apart?

    HorseSystem(Game game) {
        super(game)
    }

    @Override
    void doAction() {

    }

    @Override
    void onEvent(Event event) {

    }
}
