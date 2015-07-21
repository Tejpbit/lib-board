package game.event
/**
 * Created by tejp on 16/07/15.
 */
class EventBus {

    List<EventListener> listeners = new LinkedList<>()

    Map<? extends Event, List<EventListener>> eventListenerMap = new HashMap<>();

    void register(EventListener listener, Event event) {
        List<EventListener> specifiedListeners = eventListenerMap.containsKey(event) ? eventListenerMap.get(event) : new ArrayList<>()
        specifiedListeners.add(listener)

    }

    void register(EventListener listener, Event event, Event[] ... events) {
        event.each {register(listener, it)}
    }

    void unregister(EventListener listener) {
        eventListenerMap.values().each { list -> list.remove(listener)}
    }

    void report(Event e) {
        eventListenerMap.get(e.getClass()).each {it.onEvent(e)}
    }
}