package uk.ac.lancaster.scc210.engine.ecs.system;

import org.jsfml.graphics.RenderTarget;
import uk.ac.lancaster.scc210.engine.ecs.Entity;

/**
 * Represents a generic interface for an type of System to implement.
 */
public interface EntitySystem {
    /**
     * Called when an Entity is added from the world.
     */
    void entityAdded(Entity entity);

    /**
     * Called when an Entity is removed from the world.
     */
    void entityRemoved(Entity entity);

    /**
     * Called World.update(). Should be used to update the entities.
     */
    void update();

    /**
     * Called in World.draw(). Should be used to draw the entities.
     *
     * @param target the surface to draw on, usually a RenderWindow
     */
    void draw(RenderTarget target);
}
