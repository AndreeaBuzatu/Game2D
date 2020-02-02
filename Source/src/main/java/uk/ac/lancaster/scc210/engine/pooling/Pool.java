package uk.ac.lancaster.scc210.engine.pooling;

import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.component.PooledComponent;
import uk.ac.lancaster.scc210.engine.service.Service;
import uk.ac.lancaster.scc210.game.prototypes.Prototype;

import java.util.Queue;

public abstract class Pool implements Service {
    protected final Queue<Entity> entities;

    private final Prototype prototype;

    protected Pool(final int capacity, final Prototype prototype) {
        this.prototype = prototype;

        entities = new FixedQueue<>(capacity);
    }

    public Entity borrowEntity() {
        Entity entity;

        if ((entity = entities.poll()) == null) {
            return create();
        }

        return entity;
    }

    protected Entity create() {
        Entity created = prototype.create();

        // Add the pooled component here to differentiate it between non-pooled and pooled
        created.addComponent(new PooledComponent(this.getClass()));

        return created;
    }

    public void returnEntity(Entity entity) {
        if (entity != null) {
            entities.offer(entity);
        }
    }
}
