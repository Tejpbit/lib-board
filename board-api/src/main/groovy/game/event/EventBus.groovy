package game.event
/**
 * Created by tejp on 16/07/15.
 */
class EventBus {

    List<EventListener> listeners = new LinkedList<>()

    Map<? extends Event, List<EventListener>> eventListenerMap = new HashMap<>();

    Event register(Class<? extends Event> eventClazz, EventListener listener) {
        List<EventListener> specifiedListeners = eventListenerMap.containsKey(event) ? eventListenerMap.get(event) : new ArrayList<>()
        specifiedListeners.add(listener)
        event
    }

    void unregister(EventListener listener) {
        eventListenerMap.values().each { list -> list.remove(listener)}
    }

    void report(Event e) {
        eventListenerMap.get(e.getClass()).each {it.onEvent(e)}
    }
}