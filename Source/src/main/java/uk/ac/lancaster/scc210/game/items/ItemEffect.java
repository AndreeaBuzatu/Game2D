package uk.ac.lancaster.scc210.game.items;

import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;

public abstract class ItemEffect {
    boolean isDead;

    ItemEffect() {
        isDead = false;
    }

    public abstract void update(Time deltaTime);

    /**
     * Describes what should happen to the entity when it collides with an item
     *
     * @param entity the entity to react to
     */
    public void react(Entity entity) {
        isDead = false;
    }

    /**
     * At certain stages (e.g. at the end of a level) the item effects are reset to the default. This should be used
     * to reset.
     *
     * @param entity the entity to reset
     */
    public void reset(Entity entity) {
    }

    public boolean isDead() {
        return isDead;
    }
}
