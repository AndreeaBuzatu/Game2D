package uk.ac.lancaster.scc210.ecs.system;

import uk.ac.lancaster.scc210.ecs.World;
import uk.ac.lancaster.scc210.ecs.component.Component;
import uk.ac.lancaster.scc210.ecs.entity.Entity;

import java.util.Set;

public abstract class IterativeSystem implements EntitySystem {
    Set<Entity> entities;

    private Class<? extends Component>[] components;

    private World world;

    @SafeVarargs
    IterativeSystem(World world, Class<? extends Component>... components) {
        this.world = world;
        this.components = components;

        entities = world.getEntitiesFor(components);
    }

    @Override
    public void entityAdded() {
        this.entities = world.getEntitiesFor(components);
    }
}
