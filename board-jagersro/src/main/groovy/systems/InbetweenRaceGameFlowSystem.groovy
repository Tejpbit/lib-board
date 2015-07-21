package systems

import events.RaceDoneEvent
import game.Game
import game.event.Event
import game.systems.ECSSystem

/**
 * Created by tejp on 16/07/15.
 */
class InbetweenRaceGameFlowSystem extends ECSSystem {

    InbetweenRaceGameFlowSystem(Game game) {
        super(game)
    }

    @Override
    void doAction() {
        // Säg till lånesystemet att ta ränta baserat på hur mycket spelarna har lånat
        // säg till att vi väntar på actions nu


    }




    void prepareBetweenRaceTurn() {

    }


}
