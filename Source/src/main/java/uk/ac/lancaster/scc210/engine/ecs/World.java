package uk.ac.lancaster.scc210.engine.ecs;

import org.jsfml.graphics.RenderTarget;
import uk.ac.lancaster.scc210.engine.ecs.system.EntitySystem;
import uk.ac.lancaster.scc210.engine.service.ServiceProvider;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type World.
 */
public class World {
    // TODO: Consider moving away from the Set type
    private final Set<Entity> entities;

    private final Set<EntitySystem> systems;

    private final ServiceProvider serviceProvider;

    /**
     * Instantiates a new World.
     */
    public World(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;

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

        systems.forEach(EntitySystem::entityChanged);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);

        systems.forEach(EntitySystem::entityChanged);
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
        Set<Class<? extends Component>> componentSet = new HashSet<>(Arrays.asList(components));

        return entities
                .parallelStream()
                .filter(entity -> {
                    Set<Class<? extends Component>> actualComponents =
                            entity.getComponents().parallelStream().map(Component::getClass).collect(Collectors.toSet());

                    return actualComponents.containsAll(componentSet);
                })
                .collect(Collectors.toSet());
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }
}
