package uk.ac.lancaster.scc210.engine.ecs;

import java.util.Collection;
import java.util.HashMap;

/**
 * Represents an Entity in the world. Each entity contains a set of Components which Systems use.
 */
public class Entity {
    private final HashMap<Class<? extends Component>, Component> components;

    /**
     * Instantiates a new Entity.
     */
    public Entity() {
        components = new HashMap<>();
    }

    /**
     * Instantiates a new Entity.
     *
     * @param components the components which the entity has
     */
    Entity(Collection<Component> components) {
        this.components = new HashMap<>();

        for (Component component : components) {
            Class<? extends Component> componentClass = component.getClass();

            if (!this.components.containsKey(componentClass)) {
                this.components.put(componentClass, component);
            }
        }
    }

    /**
     * Gets components which the entity has.
     *
     * @return the components
     */
    HashMap<Class<? extends Component>, Component> getComponents() {
        return components;
    }

    public boolean hasComponent(Class<? extends Component> component) {
        return components.containsKey(component);
    }

    /**
     * Given a component class, find an instance of the component for the current Entity.
     *
     * @param component the component to search for
     * @return the component found or null
     */
    public Component findComponent(Class<? extends Component> component) {
        return components.getOrDefault(component, null);
    }
}
