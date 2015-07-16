package game.event
/**
 * Created by tejp on 16/07/15.
 */
class EventBus {

    List<EventListener> listeners = new LinkedList<>()

    void addListener(EventListener listener) {
        listeners << listener
    }

    void report(Event e) {
        listeners.each {it.onEvent(e)}
    }
}
