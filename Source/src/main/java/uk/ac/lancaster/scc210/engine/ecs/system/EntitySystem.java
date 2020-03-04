package uk.ac.lancaster.scc210.engine.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;

import java.util.Collection;

/**
 * Represents a generic interface for an type of System to implement.
 */
public interface EntitySystem {
    /**
     * Called when an Entity is added from the world.
     *
     * @param entity the entity
     */
    void entityAdded(Entity entity);

    /**
     * Entities added.
     *
     * @param entities the entities
     */
    void entitiesAdded(Collection<? extends Entity> entities);

    /**
     * Called when an Entity is removed from the world.
     *
     * @param entity the entity
     */
    void entityRemoved(Entity entity);

    /**
     * Called World.update(). Should be used to update the entities.
     *
     * @param deltaTime deltaTime to update
     */
    void update(Time deltaTime);

    /**
     * Called in World.draw(). Should be used to draw the entities.
     *
     * @param target the surface to draw on, usually a RenderWindow
     */
    void draw(RenderTarget target);
}
