package resources

import events.ChanceCardEvent
import events.GallopCardEvent
import events.MoveHorseEvent
import game.Game
import game.components.*
import game.entitys.Entity

import java.awt.Color


/**
 * Created by tejp on 17/07/15.
 */
class ResourceBuilder {

    private static final Color[] horseColors = [Color.RED, Color.BLACK, Color.BLUE, Color.YELLOW, Color.WHITE] as Color[]
    private static int horseColorIndex = 0;

    static Entity createHorse(String name, Entity owner) {
        Entity e = new Entity()
        def intValues = new IntegerValues()
        intValues.values.put("value", 2500)
        intValues.values.put("wins", 0)
        intValues.values.put("age", 2)

        def strValues = new StringValues()
        strValues.values.put("name", name)

        e.components.put(Ownable.class, new Ownable(owner: owner))
        e.components.put(IntegerValues.class, intValues)
        e.components.put(StringValues.class, strValues)
        e.components.put(ECSColor.class, new ECSColor(horseColors[horseColorIndex]))
        horseColorIndex = (horseColorIndex + 1) % horseColors.length
        e.components.put(DataTable.class, new DataTable<Integer>(
                "speedStats",
                [2,3,2,4,3,2,3,4,2,2,3,2] as Integer[]
        ))

        e
    }

    private static char stable = 'A'

    static Entity createPlayer(String name) {
        Entity e = new Entity()
        def intValues = new IntegerValues()
        intValues.values.put("loan", 2000)
        intValues.values.put("money", 2000)

        def strValues = new StringValues()
        strValues.put("name", name)
        strValues.put("stable", stable++)


        e.components.put(IntegerValues.class, intValues)
        e.components.put(StringValues.class, strValues)
        e.components.put(Inventory.class, new Inventory())
        e.components.put(DataMap.class, new DataMap<Color, Integer>("totalisator"))

        e
    }

    static Entity createBank() {
        Entity e = new Entity()
        e.components.put(Inventory.class, new Inventory())
        def strValues = new StringValues()
        strValues.values.put("bank", "")
        e.components.put(StringValues.class, strValues)
        e
    }

    static Collection<Entity> createChanceCards(Game game) {
        List<Entity> chanceCards = new ArrayList<>()

        def card1 = new Entity()
        def strValues = new StringValues()
        strValues.values.put("name", "Chanskort")
        strValues.values.put("text", "Din häst är sämst. Flytta hästen bakåt 3 steg.")

        card1.components.put(StringValues.class, strValues)
        card1.components.put(Effect.class, {entity -> game.eventBus.report(new MoveHorseEvent(horse: entity, steps: -3))} as Effect)
        chanceCards << card1
    }

    static Collection<Entity> createGallopCards() {
        List<Entity> gallopCards = new ArrayList<>()

        def card1 = new Entity()
        def strValues = new StringValues()
        strValues.values.put("name", "Gallopkort")
        strValues.values.put("text", "Din häst är bastastast. Flytta hästen frammåt 4 steg.")

        card1.components.put(StringValues.class, strValues)
        card1.components.put(Effect.class, {entity -> game.eventBus.report(new MoveHorseEvent(horse: entity, steps: 4))} as Effect)
        gallopCards << card1
    }

    static List<Entity> createRaceTrack(int yearCohort, int[] chanceCardTileIndexes, int[] gallopCardTileIndexes) {

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
}