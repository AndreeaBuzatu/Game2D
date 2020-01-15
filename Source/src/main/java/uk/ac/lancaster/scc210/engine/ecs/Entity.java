package uk.ac.lancaster.scc210.engine.ecs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Entity {
    private final List<Component> components;

    public Entity() {
        components = new ArrayList<>();
    }

    Entity(Collection<Component> components) {
        this.components = new ArrayList<>();

        this.components.addAll(components);
    }

    List<Component> getComponents() {
        return components;
    }

    public Component findComponent(Class<? extends Component> component) {
        return components
                .stream()
                .filter(component::isInstance)
                .findAny()
                .orElse(null);
    }
}
