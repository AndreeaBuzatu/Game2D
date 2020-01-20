package uk.ac.lancaster.scc210.engine.ecs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The type Entity.
 */
public class Entity {
    private final List<Component> components;

    /**
     * Instantiates a new Entity.
     */
    public Entity() {
        components = new ArrayList<>();
    }

    /**
     * Instantiates a new Entity.
     *
     * @param components the components
     */
    Entity(Collection<Component> components) {
        this.components = new ArrayList<>();

        this.components.addAll(components);
    }

    /**
     * Gets components.
     *
     * @return the components
     */
    List<Component> getComponents() {
        return components;
    }

    /**
     * Find component component.
     *
     * @param component the component
     * @return the component
     */
    public Component findComponent(Class<? extends Component> component) {
        return components
                .stream()
                .filter(component::isInstance)
                .findAny()
                .orElse(null);
    }
}
