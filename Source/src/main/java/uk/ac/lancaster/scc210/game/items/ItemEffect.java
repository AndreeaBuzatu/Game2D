package uk.ac.lancaster.scc210.game.items;

import uk.ac.lancaster.scc210.engine.ecs.Entity;

/**
 * This interfaces describes how an item should react after it has been collided with.
 */
public interface ItemEffect {
    /**
     * Describes what should happen to the entity when it collides with an item
     *
     * @param entity the entity to react to
     */
    void react(Entity entity);

    /**
     * At certain stages (e.g. at the end of a level) the item effects are reset to the default. This should be used
     * to reset.
     *
     * @param entity the entity to reset
     */
    void reset(Entity entity);
}
