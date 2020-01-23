package uk.ac.lancaster.scc210.engine.ecs;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Entity.
 */
public class Entity {
    private final Set<Component> components;

    /**
     * Instantiates a new Entity.
     */
    public Entity() {
        components = new HashSet<>();
    }

    /**
     * Instantiates a new Entity.
     *
     * @param components the components
     */
    Entity(Collection<Component> components) {
        this.components = new HashSet<>();

        this.components.addAll(components);
    }

    /**
     * Gets components.
     *
     * @return the components
     */
    Set<Component> getComponents() {
        return components;
    }

    public boolean hasComponent(Class<? extends Component> component) {
        return findComponent(component) != null;
    }

    /**
     * Find component component.
     *
     * @param component the component
     * @return the component
     */
    public Component findComponent(Class<? extends Component> component) {
        return components
                .parallelStream()
                .filter(component::isInstance)
                .findAny()
                .orElse(null);
    }
}
