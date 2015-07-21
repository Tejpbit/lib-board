package systems

import events.RaceDoneEvent
import events.RaceStartEvent
import game.Game
import game.components.GamePiece
import game.components.IntegerValues
import game.components.Ownable
import game.components.Player
import game.components.StringValues
import game.entitys.Entity
import game.event.Event
import game.event.EventListener
import game.systems.ECSSystem

import java.security.acl.Owner
import java.util.stream.Collectors

/**
 * Created by tejp on 16/07/15.
 */
class RaceGameFlowSystem extends ECSSystem {



    List<Entity> racingHorses = new ArrayList<>()
    List<Entity> racingPlayers = new ArrayList<>()
    int playerTurn = 0

    RaceGameFlowSystem(Game game) {
        super(game)

        listeners << game.eventBus.register(RaceStartEvent.class, {RaceStartEvent ev -> startRace() })
    }

    @Override
    void doAction() {

        // rotera bland spelarna i den ordningen som hittades.
        // spelaren får välja vilken häst han vill flytta. alla måste flyttas.
    }

    /**
     * Collect necessary components to perform race.
     * Also place horses at starting positions for corresponding yearCohort
     */
    void startRace() {

        racingHorses = game.entitys.stream()
                .filter({ it.components.containsKey(GamePiece.class) })
                .collect(Collectors.toList())

        racingPlayers =
                racingHorses.stream()
                        .map({h -> (h.components.get(Owner.class) as Ownable).owner})
                        .distinct()
                        .sorted(Comparator.comparing({Entity e -> (e.components.get(StringValues.class) as StringValues).values.get("stable") }))
                        .collect(Collectors.toList())


        List<Entity> trackStarts =
                game.entitys.stream()
                        .filter({e -> (e.components.get(IntegerValues.class) as IntegerValues).values.get("trackIndex") == 0 })
                        .collect(Collectors.toList())


        racingHorses.each { horse ->
            int yearCohort = (horse.components.get(IntegerValues.class) as IntegerValues).values.get("yearCohort")

            Entity matchingStartTrack = trackStarts.find {
                (it.components.get(IntegerValues.class) as IntegerValues).values.get("yearCohort") == yearCohort
            }

            (horse.components.get(GamePiece.class) as GamePiece).tileEntity = matchingStartTrack
        }

    }


}
