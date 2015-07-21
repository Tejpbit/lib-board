import events.ChanceCardEvent
import events.GallopCardEvent
import game.Game
import game.components.Effect
import game.components.IntegerValues
import game.components.Tile
import game.entitys.Entity
import game.systems.ECSSystem
import resources.ResourceBuilder
import systems.CardHandlerSystem
import systems.HorseSystem
import systems.InbetweenRaceGameFlowSystem
import systems.LoanSystem
import systems.RaceGameFlowSystem

/**
 * Created by tejp on 16/07/15.
 */
class Main {

    Game game;
 /*
    List<Entity> players = new ArrayList<>()
    Map<Integer, Entity[]> raceTrack = new HashMap<>()
    List<Entity> gallopCards = new ArrayList<>()
    List<Entity> chanceCards = new ArrayList<>()
    List<Entity> horses = new ArrayList<>()
    List<Entity> treasuryBonds = new ArrayList<>()
    List<Entity> firms = new ArrayList<>()
*/
    Main () {
        game = new Game()
        game.entitys.addAll(createPlayers())
        Entity bank =  ResourceBuilder.createBank()
        game.entitys << bank
        game.entitys.addAll(createHorses(bank))
        game.entitys.addAll(ResourceBuilder.createChanceCards())
        game.entitys.addAll(ResourceBuilder.createGallopCards())
        game.entitys.addAll(createRaceTrack(2, [4,9,14,19,25,29] as int[], [2,6,12,17,22,27] as int[]))
        game.entitys.addAll(createRaceTrack(3, [4,10,16,21,29,33] as int[], [2,6,14,19,25,31] as int[]))
        game.entitys.addAll(createRaceTrack(4, [4,11,18,23,33,37] as int[], [2,6,16,21,28,35] as int[]))
        game.entitys.addAll(createRaceTrack(5, [4,12,20,25,37,41] as int[], [2,6,18,23,31,39] as int[]))
        game.systems.addAll(createSystems())
    }


    List<Entity> createRaceTrack(int yearCohort, int[] chanceCardTileIndexes, int[] gallopCardTileIndexes) {

        int length = 32 + 4 * (yearCohort - 2)

        int[] multiplier = [3,4,5,10,20] as int[]

        Entity[] track = new Entity[length]
        Tile[] tiles = new Tile[length]

        for (int i = 0; i < length; i++) {
            tiles[i] = new Tile()
        }


        for (int i = 0; i < length; i++) {
            track[i] = new Entity()
            def intValues = new IntegerValues()
            intValues.values.put("trackIndex", i)
            intValues.values.put("yearCohort", yearCohort)
            if (chanceCardTileIndexes.contains(i)) {
                track[i].components.put(Effect.class, {entity -> game.eventBus.report(new ChanceCardEvent(horse: entity))} as Effect)
            }
            if (gallopCardTileIndexes.contains(i)) {
                track[i].components.put(Effect.class, {entity -> game.eventBus.report(new GallopCardEvent(horse: entity))} as Effect)
            }

            if ((length - i) <= 5) {
                intValues.values.put("multiplier", multiplier[(length - i) - 1])
            } else {
                intValues.values.put("multiplier", 2)
            }

            track[i].components.put(IntegerValues.class, intValues)
            track[i].components.put(Tile.class, tiles[i])
        }

        for (int i = 0; i < length; i++) {
            if (i != 0) {
                tiles[i].predecessors << track[i-1]
            }
            if (i != length - 1) {
                tiles[i].successors << track[i+1]
            }
        }

        track
    }

    void createSystems() {
        List<ECSSystem> systems = new ArrayList<>()
        systems << new CardHandlerSystem(game)
        systems << new HorseSystem(game)
        systems << new InbetweenRaceGameFlowSystem(game)
        systems << new RaceGameFlowSystem(game)
        systems << new LoanSystem(game)
    }

    List<Entity> createPlayers() {
        List<Entity> players = new ArrayList<>()
        players << ResourceBuilder.createPlayer("Tejp")
        players << ResourceBuilder.createPlayer("Mink")
        players << ResourceBuilder.createPlayer("Zomis")
        players << ResourceBuilder.createPlayer("Etrisa")
    }

    List<Entity> createHorses(Entity owner) {
        List<Entity> horses = new ArrayList<>()
        horses << ResourceBuilder.createHorse("Maaaax", owner)
        horses << ResourceBuilder.createHorse("Bubu", owner)
        horses << ResourceBuilder.createHorse("Slödder", owner)
        horses << ResourceBuilder.createHorse("Minkluder", owner)
        horses << ResourceBuilder.createHorse("EnPonnie", owner)
    }
}
