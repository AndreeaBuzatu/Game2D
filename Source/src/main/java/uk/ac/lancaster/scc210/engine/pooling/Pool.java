package uk.ac.lancaster.scc210.engine.pooling;

import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.service.Service;

import java.util.Queue;

public abstract class Pool implements Service {
    protected final Queue<Entity> entities;

    protected Pool(final int capacity) {
        entities = new FixedQueue<>(capacity);
    }

    protected abstract Entity create();

    public Entity borrowEntity() {
        Entity entity;

        if ((entity = entities.poll()) == null) {
            return create();
        }

        return entity;
    }

    public void returnEntity(Entity entity) {
        if (entity != null) {
            entities.offer(entity);
        }
    }
}
