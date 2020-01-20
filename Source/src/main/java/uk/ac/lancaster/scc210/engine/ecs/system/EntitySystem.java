package uk.ac.lancaster.scc210.engine.ecs.system;

import org.jsfml.graphics.RenderTarget;

/**
 * The interface Entity system.
 */
public interface EntitySystem {
    /**
     * Entity added.
     */
    void entityAdded();

    /**
     * Update.
     */
    void update();

    /**
     * Draw.
     *
     * @param target the target
     */
    void draw(RenderTarget target);
}
