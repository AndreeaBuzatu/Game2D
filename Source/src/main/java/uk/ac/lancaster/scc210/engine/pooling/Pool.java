package uk.ac.lancaster.scc210.engine.pooling;

import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.component.PooledComponent;
import uk.ac.lancaster.scc210.engine.prototypes.Prototype;
import uk.ac.lancaster.scc210.engine.service.Service;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Pool implements Service {
    protected final Queue<Entity> entities;

    private final Prototype prototype;

    private final int capacity;

    protected Pool(final int capacity, final Prototype prototype) {
        this.capacity = capacity;
        this.prototype = prototype;

        entities = new LinkedList<>();

        for (int i = 0; i < capacity; i++) {
            entities.add(create());
        }
    }

    /**
     * Borrow an entity with the default entity given
     *
     * @return the borrowed entity
     */
    protected Entity borrowEntity() {
        if (!entities.isEmpty()) {
            return entities.remove();
        }

        return create();
    }

    /**
     * Borrow an entity with a given name from a manager (or somewhere else)
     *
     * @param entityName the entity to borrow
     * @return the borrowed entity
     */
    public Entity borrowEntity(String entityName) {
        if (!entities.isEmpty()) {
            return entities.remove();
        }

        return create(entityName);
    }

    /**
     * Create an entity with the default value
     * @return the created entity
     */
    protected Entity create() {
        Entity created = prototype.create();

        // Add the pooled component here to differentiate it between non-pooled and pooled
        created.addComponent(new PooledComponent(this.getClass()));

        return created;
    }

    public void returnEntity(Entity entity) {
        if (entity != null && entities.size() < capacity) {
            entities.add(entity);
        }
    }

    /**
     * Create an entity with a name given from a manager (or somewhere else)
     *
     * @param entityName the entity to create
     * @return the created entity
     */
    protected abstract Entity create(String entityName);
}
