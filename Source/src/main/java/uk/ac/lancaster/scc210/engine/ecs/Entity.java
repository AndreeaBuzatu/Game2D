package uk.ac.lancaster.scc210.engine.ecs;

import java.util.Collection;
import java.util.HashMap;

/**
 * The type Entity.
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
     * @param components the components
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
     * Gets components.
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
     * Find component component.
     *
     * @param component the component
     * @return the component
     */
    public Component findComponent(Class<? extends Component> component) {
        return components.getOrDefault(component, null);
    }
}
