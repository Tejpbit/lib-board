package systems

import events.MoveHorseEvent
import game.Game
import game.event.Event
import game.event.EventListener

import game.systems.ECSSystem

/**
 * Created by tejp on 17/07/15.
 */
class CardHandlerSystem extends ECSSystem implements EventListener<Event> { // TODO add eventclass for this CardEvent or CardPickedEvent etc...

    CardHandlerSystem(Game game) {
        super(game)
    }

    @Override
    void doAction() {

    }

    @Override
    void onEvent(Event event) {
        if (event instanceof MoveHorseEvent) {

        }
    }
}
