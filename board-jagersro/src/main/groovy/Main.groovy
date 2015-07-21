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
        Entity bank = ResourceBuilder.createBank()
        game.entitys << bank
        game.entitys.addAll(createHorses(bank))
        game.entitys.addAll(ResourceBuilder.createChanceCards())
        game.entitys.addAll(ResourceBuilder.createGallopCards())
        game.entitys.addAll(ResourceBuilder.createRaceTrack(2, [4,9,14,19,25,29] as int[], [2,6,12,17,22,27] as int[]))
        game.entitys.addAll(ResourceBuilder.createRaceTrack(3, [4,10,16,21,29,33] as int[], [2,6,14,19,25,31] as int[]))
        game.entitys.addAll(ResourceBuilder.createRaceTrack(4, [4,11,18,23,33,37] as int[], [2,6,16,21,28,35] as int[]))
        game.entitys.addAll(ResourceBuilder.createRaceTrack(5, [4,12,20,25,37,41] as int[], [2,6,18,23,31,39] as int[]))
        game.systems.addAll(createSystems())
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
