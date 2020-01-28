package uk.ac.lancaster.scc210.engine.ecs.system;

import org.jsfml.graphics.RenderTarget;

/**
 * Represents a generic interface for an type of System to implement.
 */
public interface EntitySystem {
    /**
     * Called when either an Entity is added or removed from the world.
     */
    void entityChanged();

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
