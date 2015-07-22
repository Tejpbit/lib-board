package systems

import events.ErrorEvent
import events.MoveHorseEvent
import events.RaceStartEvent
import game.Game
import game.components.DataTable
import game.components.GamePiece
import game.components.IntegerValues
import game.components.Ownable
import game.components.StringValues
import game.components.Tile
import game.entitys.Entity
import game.systems.ECSSystem

import java.util.stream.Collectors

/**
 * Created by tejp on 16/07/15.
 */
class RaceGameFlowSystem extends ECSSystem {



    List<Entity> racingHorses = new ArrayList<>()
    List<Entity> racingPlayers = new ArrayList<>()
    int playerTurn = 0
    int moveTurn = 0

    RaceGameFlowSystem(Game game) {
        super(game)

        listeners << game.eventBus.register(RaceStartEvent, {RaceStartEvent ev -> startRace() })
        listeners << game.eventBus.register(MoveHorseEvent, {MoveHorseEvent ev -> moveHorse(ev) })
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
                .filter({ it.components.containsKey(GamePiece) })
                .collect(Collectors.toList())

        racingPlayers =
                racingHorses.stream()
                        .map({h -> (h.components.get(Ownable) as Ownable).owner})
                        .distinct()
                        .sorted(Comparator.comparing({Entity e -> (e.components.get(StringValues) as StringValues).values.get("stable") }))
                        .collect(Collectors.toList())


        List<Entity> trackStarts =
                game.entitys.stream()
                        .filter({e -> (e.components.get(IntegerValues) as IntegerValues).values.get("trackIndex") == 0 })
                        .collect(Collectors.toList())


        racingHorses.each { horse ->
            int yearCohort = (horse.components.get(IntegerValues) as IntegerValues).values.get("yearCohort")

            Entity matchingStartTrack = trackStarts.find {
                (it.components.get(IntegerValues) as IntegerValues).values.get("yearCohort") == yearCohort
            }

            (horse.components.get(GamePiece) as GamePiece).tileEntity = matchingStartTrack
        }


    }

    void moveHorse(MoveHorseEvent ev) {
        Entity owner =  (ev.horse.components.get(Ownable) as Ownable).owner
        if ( owner !=  racingPlayers.get(playerTurn) ) {
            String msg = String.format("Not your turn %s. Current player is %s",
                    (owner.components.get(StringValues) as StringValues).values.get("name"),
                    (racingPlayers.get(playerTurn).components.get(StringValues) as StringValues).values.get("name"))
            game.eventBus.report(new ErrorEvent(msg: msg))

        } else {
            DataTable<Integer> speedTable = ev.horse.components.get(DataTable)
            int move = speedTable.data[moveTurn]

            Entity currentTile = (ev.horse.components.get(GamePiece) as GamePiece).tileEntity

            while(move--) {
                currentTile = (currentTile.components.get(Tile) as Tile).successors.iterator().next()
            }

        }
    }

    void nextPlayerTurn() {
        playerTurn = (playerTurn + 1) % racingPlayers.size()
    }

    void nextMoveTurn() {
        moveTurn++
    }


}
