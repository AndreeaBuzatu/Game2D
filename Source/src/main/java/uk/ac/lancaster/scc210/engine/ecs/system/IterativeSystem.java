package uk.ac.lancaster.scc210.engine.ecs.system;

import uk.ac.lancaster.scc210.engine.ecs.Component;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;

import java.util.Set;

public abstract class IterativeSystem implements EntitySystem {
    protected Set<Entity> entities;

    private final Class<? extends Component>[] components;

    private final World world;

    @SafeVarargs
    protected IterativeSystem(World world, Class<? extends Component>... components) {
        this.world = world;
        this.components = components;

        entities = world.getEntitiesFor(components);
    }

    @Override
    public void entityAdded() {
        this.entities = world.getEntitiesFor(components);
    }
}
