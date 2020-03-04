package uk.ac.lancaster.scc210.engine.collision;

import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.OrientatedBoxComponent;

import java.util.HashSet;
import java.util.Set;

/**
 * The type Cell.
 */
public class Cell {
    private final Set<Set<Entity>> collisions;

    private final Set<Entity> entities;

    private Set<Entity> collision;

    /**
     * Instantiates a new Cell.
     */
    Cell() {
        collisions = new HashSet<>();

        entities = new HashSet<>();

        collision = new HashSet<>();
    }

    /**
     * Check collision set.
     *
     * @return the set
     */
    public Set<Set<Entity>> checkCollision() {
        collisions.clear();

        collision.clear();

        for (Entity outer : entities) {
            OrientatedBoxComponent outerOrientatedBoxComponent = (OrientatedBoxComponent) outer.findComponent(OrientatedBoxComponent.class);

            for (Entity inner : entities) {
                if (outer == inner) {
                    continue;
                }

                OrientatedBoxComponent innerOrientatedBoxComponent = (OrientatedBoxComponent) inner.findComponent(OrientatedBoxComponent.class);

                if (OrientatedBox.areColliding(outerOrientatedBoxComponent.getOrientatedBox(), innerOrientatedBoxComponent.getOrientatedBox())) {
                    collision.add(outer);

                    collision.add(inner);

                    collisions.add(collision);
                }
            }
        }

        return collisions;
    }

    /**
     * Add entity.
     *
     * @param entity the entity
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    /**
     * Clear.
     */
    public void clear() {
        entities.clear();
    }

    /**
     * Gets entities.
     *
     * @return the entities
     */
    public Set<Entity> getEntities() {
        return entities;
    }
}
