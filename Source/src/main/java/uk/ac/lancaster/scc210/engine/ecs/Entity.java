package uk.ac.lancaster.scc210.engine.ecs;

import java.util.Collection;
import java.util.HashMap;

/**
 * Represents an Entity in the world. Each entity contains a set of Components which Systems use.
 */
public class Entity {
    private static int ID_INC = 0;
    
    private final HashMap<Class<? extends Component>, Component> components;

    private final int id;

    /**
     * Instantiates a new Entity.
     */
    public Entity() {
        components = new HashMap<>();

        id = ID_INC++;
    }

    /**
     * Instantiates a new Entity.
     *
     * @param components the components which the entity has
     */
    Entity(Collection<Component> components) {
        this.components = new HashMap<>();

        id = ID_INC++;

        for (Component component : components) {
            Class<? extends Component> componentClass = component.getClass();

            if (!this.components.containsKey(componentClass)) {
                this.components.put(componentClass, component);
            }
        }
    }

    public void addComponent(Component component) {
        Class<? extends Component> componentClass = component.getClass();

        if (!this.components.containsKey(componentClass)) {
            components.put(componentClass, component);
        }
    }

    public void removeComponent(Class<? extends Component> component) {
        components.remove(component);
    }

    /**
     * Gets components which the entity has.
     *
     * @return the components
     */
    public HashMap<Class<? extends Component>, Component> getComponents() {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Entity entityObj = (Entity) obj;

        return entityObj.id == id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
