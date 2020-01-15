package uk.ac.lancaster.scc210.ecs.entity;

import uk.ac.lancaster.scc210.ecs.component.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Entity {
    private final List<Component> components;

    public Entity() {
        components = new ArrayList<>();
    }

    public Entity(Collection<Component> components) {
        this.components = new ArrayList<>();

        this.components.addAll(components);
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public List<Component> getComponents() {
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
