package uk.ac.lancaster.scc210.engine.ecs;

import org.jsfml.graphics.RenderTarget;
import uk.ac.lancaster.scc210.engine.ecs.system.EntitySystem;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.engine.service.ServiceProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * World contains all the entities and systems in the game.
 */
public class World {
    private final ArrayList<Entity> entities;

    private final ArrayList<EntitySystem> systems;

    private final ArrayList<Pool> pools;

    private final ServiceProvider serviceProvider;

    /**
     * Instantiates a new World.
     */
    public World(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;

        entities = new ArrayList<>();

        systems = new ArrayList<>();

        pools = new ArrayList<>();
    }

    /**
     * Add entity to the world. The world will update all the systems that a new entity has been added.
     *
     * @param entity the entity
     */
    public void addEntity(Entity entity) {
        if (!entities.contains(entity)) {
            entities.add(entity);

            systems.forEach(EntitySystem::entityChanged);
        }
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);

        systems.forEach(EntitySystem::entityChanged);
    }

    /**
     * Add a System to the world. It will be iterated upon in the next loop.
     *
     * @param system the system to add
     */
    public void addSystem(EntitySystem system) {
        if (!systems.contains(system)) {
            systems.add(system);
        }
    }

    /**
     * Add a pool to the world. Should be added before any systems are added.
     *
     * @param pool the pool to add
     */
    public void addPool(Pool pool) {
        if (!pools.contains(pool)) {
            pools.add(pool);
        }
    }

    /**
     * Update all the Systems contained in World.
     */
    public void update() {
        for (EntitySystem system : systems) {
            system.update();
        }
    }

    /**
     * Draw all the Systems contained in World.
     *
     * @param target the target. Usually RenderWindow
     */
    public void draw(RenderTarget target) {
        for (EntitySystem system : systems) {
            system.draw(target);
        }
    }

    /**
     * Create an Entity from a given array of Components.
     *
     * @param components components to add to the new entity.
     * @return the new entity
     */
    public static Entity createEntity(Component... components) {
        return new Entity(Arrays.asList(components));
    }

    /**
     * Given an array of components, find all the entities which have that set
     *
     * @param components the components
     * @return the entities for
     */
    @SafeVarargs
    public final Set<Entity> getEntitiesFor(Class<? extends Component>... components) {
        return entities
                .parallelStream()
                .filter(entity -> entity.getComponents().keySet().containsAll(Arrays.asList(components)))
                .collect(Collectors.toSet());
    }

    /**
     * From a Class<Pool> return the associate Pool
     *
     * @param klass the Pool to find
     * @return the Pool or null if the Pool can't be found
     */
    public Pool getPool(Class<? extends Pool> klass) {
        return pools
                .parallelStream()
                .filter(pool -> pool.getClass().equals(klass))
                .findFirst()
                .orElse(null);
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }
}
