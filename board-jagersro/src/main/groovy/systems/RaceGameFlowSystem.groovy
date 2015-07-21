package systems

import events.RaceDoneEvent
import game.Game
import game.components.GamePiece
import game.components.Ownable
import game.entitys.Entity
import game.event.Event
import game.systems.ECSSystem

import java.security.acl.Owner
import java.util.stream.Collectors

/**
 * Created by tejp on 16/07/15.
 */
class RaceGameFlowSystem extends ECSSystem {


    List<Entity> players = new ArrayList<>()

    RaceGameFlowSystem(Game game) {
        super(game)
    }

    @Override
    void doAction() {

        List<Entity> horses = game.entitys.stream()
                .filter({ it.components.containsKey(GamePiece.class) })
                .collect(Collectors.toList())



        horses.stream().
        horses.each {
            it.components.get(Ownable.class)
        }
//        List<Entity> players = horses.



        /*
        hämta alla spelare som har hästar i spelet.
        sortera dem efter stall A B C ...
         */




        // rotera bland spelarna i den ordningen som hittades.
        // spelaren får välja vilken häst han vill flytta. alla måste flyttas.
    }

    @Override
    void onEvent(Event event) {

        doAction()
        game.eventBus.report(new RaceDoneEvent())

    }
}
