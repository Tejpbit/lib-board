package game.entitys

import game.components.Component

/**
 * Created by tejp on 16/07/15.
 */
class Entity {
    Map<Class<? extends Component>, Component> components = new HashMap<>();

    void addComponent(Class<? extends Component> clazz, Component component) {
        components.put(clazz, component)

    }
}
