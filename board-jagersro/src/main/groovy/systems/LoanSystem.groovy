package systems

import game.Game
import game.components.Player
import game.event.Event
import game.event.EventListener
import game.systems.ECSSystem

/**
 * Created by tejp on 16/07/15.
 */
class LoanSystem extends ECSSystem implements EventListener<Event> { //TODO take loan event?

    LoanSystem(Game game) {
        super(game)
    }

    @Override
    void doAction() {
        game.entitys.stream().filter({it.components.containsKey(Player)})
        // Säg till lånesystemet att ta ränta baserat på hur mycket spelarna har lånat
        // säg till att vi väntar på actions nu


    }

    @Override
    void onEvent(Event event) {

    }
}
