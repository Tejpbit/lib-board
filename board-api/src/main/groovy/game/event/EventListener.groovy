package game.event

/**
 * Created by tejp on 16/07/15.
 */
@FunctionalInterface
interface EventListener<T extends Event> {
    void onEvent(T event)
}
