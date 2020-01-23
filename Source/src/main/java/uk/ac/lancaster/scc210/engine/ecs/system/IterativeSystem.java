package uk.ac.lancaster.scc210.engine.ecs.system;

import uk.ac.lancaster.scc210.engine.ecs.Component;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;

import java.util.Set;

/**
 * The type Iterative system.
 */
public abstract class IterativeSystem implements EntitySystem {
    /**
     * The Entities.
     */
    protected Set<Entity> entities;

    private final Class<? extends Component>[] components;

    protected final World world;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world      the world
     * @param components the components
     */
    @SafeVarargs
    protected IterativeSystem(World world, Class<? extends Component>... components) {
        this.world = world;
        this.components = components;

        entities = world.getEntitiesFor(components);
    }

    @Override
    public void entityChanged() {
        this.entities = world.getEntitiesFor(components);
    }
}
