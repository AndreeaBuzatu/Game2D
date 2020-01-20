package uk.ac.lancaster.scc210.engine.ecs;

import org.jsfml.graphics.RenderTarget;
import uk.ac.lancaster.scc210.engine.ecs.system.EntitySystem;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type World.
 */
public class World {
    private final Set<Entity> entities;

    private final Set<EntitySystem> systems;

    /**
     * Instantiates a new World.
     */
    public World() {
        entities = new LinkedHashSet<>();

        systems = new LinkedHashSet<>();
    }

    /**
     * Add entity.
     *
     * @param entity the entity
     */
    public void addEntity(Entity entity) {
        entities.add(entity);

        systems.forEach(EntitySystem::entityAdded);
    }

    /**
     * Add system.
     *
     * @param system the system
     */
    public void addSystem(EntitySystem system) {
        systems.add(system);
    }

    /**
     * Update.
     */
    public void update() {
        for (EntitySystem system : systems) {
            system.update();
        }
    }

    /**
     * Draw.
     *
     * @param target the target
     */
    public void draw(RenderTarget target) {
        for (EntitySystem system : systems) {
            system.draw(target);
        }
    }

    /**
     * Create entity entity.
     *
     * @param components the components
     * @return the entity
     */
    public static Entity createEntity(Component... components) {
        return new Entity(Arrays.asList(components));
    }

    /**
     * Gets entities for.
     *
     * @param components the components
     * @return the entities for
     */
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
