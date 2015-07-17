package game.components

import game.event.Event
import game.event.EventBus

/**
 * Created by tejp on 16/07/15.
 */
class EventTrigger implements Component{

    Event e
    EventTrigger(Event e) {
        this.e = e
    }

    void trigger() {
        EventBus.INSTANCE.report(e)
    }
}
