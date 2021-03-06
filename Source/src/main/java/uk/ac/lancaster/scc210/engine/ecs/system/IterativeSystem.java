package uk.ac.lancaster.scc210.engine.ecs.system;

import uk.ac.lancaster.scc210.engine.ecs.Component;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;

import java.util.Set;

/**
 * Used for Systems which simply iterate through the entities in the World.
 */
public abstract class IterativeSystem implements EntitySystem {
    /**
     * The Set of Entities given to the system from World.
     */
    protected Set<Entity> entities;

    private final Class<? extends Component>[] components;

    /**
     * The World.
     */
    protected final World world;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world      the world containing entities to use
     * @param components the components which this system uses
     */
    @SafeVarargs
    protected IterativeSystem(World world, Class<? extends Component>... components) {
        this.world = world;
        this.components = components;

        entities = world.getEntitiesFor(components);
    }

    /*
    @Override
    public void entityAdded(Entity entity) {
        this.entities = world.getEntitiesFor(components);
    }

    @Override
    public void entitiesAdded(Collection<? extends Entity> entities) {
        this.entities = world.getEntitiesFor(components);
    }

    @Override
    public void entityRemoved(Entity entity) {
        this.entities = world.getEntitiesFor(components);
    }
     */
}
