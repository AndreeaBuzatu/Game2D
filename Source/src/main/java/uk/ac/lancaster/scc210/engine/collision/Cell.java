package uk.ac.lancaster.scc210.engine.collision;

import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.OrientatedBoxComponent;

import java.util.HashSet;
import java.util.Set;

/**
 * The type Cell.
 */
public class Cell {
    private final Set<Entity> entities;

    /**
     * Instantiates a new Cell.
     */
    Cell() {
        entities = new HashSet<>();
    }

    /**
     * Check collision set.
     *
     * @return the set
     */
    public Set<Set<Entity>> checkCollision() {
        Set<Set<Entity>> collisions = new HashSet<>();

        for (Entity outer : entities) {
            OrientatedBoxComponent outerOrientatedBoxComponent = (OrientatedBoxComponent) outer.findComponent(OrientatedBoxComponent.class);

            for (Entity inner : entities) {
                if (outer == inner) {
                    continue;
                }

                OrientatedBoxComponent innerOrientatedBoxComponent = (OrientatedBoxComponent) inner.findComponent(OrientatedBoxComponent.class);

                if (OrientatedBox.areColliding(outerOrientatedBoxComponent.getOrientatedBox(), innerOrientatedBoxComponent.getOrientatedBox())) {
                    collisions.add(Set.of(outer, inner));
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
