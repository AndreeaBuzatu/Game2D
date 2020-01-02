package uk.ac.lancaster.scc210.ecs;

import org.jsfml.graphics.RenderTarget;
import uk.ac.lancaster.scc210.ecs.component.Component;
import uk.ac.lancaster.scc210.ecs.entity.Entity;
import uk.ac.lancaster.scc210.ecs.system.EntitySystem;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class World {
    private Set<Entity> entities;

    private Set<EntitySystem> systems;

    public World() {
        entities = new LinkedHashSet<>();

        systems = new LinkedHashSet<>();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);

        systems.forEach(EntitySystem::entityAdded);
    }

    public void addSystem(EntitySystem system) {
        systems.add(system);
    }

    public void update() {
        for (EntitySystem system : systems) {
            system.update();
        }
    }

    public void draw(RenderTarget target) {
        for (EntitySystem system : systems) {
            system.draw(target);
        }
    }

    public Entity createEntity(Component... components) {
        return new Entity(Arrays.asList(components));
    }

    @SafeVarargs
    public final Set<Entity> getEntitiesFor(Class<? extends Component>... components) {
        return entities
                .parallelStream()
                .filter(entity -> {
                    Set<Class<? extends Component>> componentSet =
                            entity.getComponents().parallelStream().map(Component::getClass).collect(Collectors.toSet());

                    return componentSet.containsAll(Arrays.asList(components));
                })
                .collect(Collectors.toSet());
    }
}
