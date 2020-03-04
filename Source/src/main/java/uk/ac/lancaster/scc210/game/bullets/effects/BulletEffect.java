package uk.ac.lancaster.scc210.game.bullets.effects;

import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Bullet effect.
 */
public abstract class BulletEffect  {
    /**
     * The Reacted entities.
     */
    final List<Entity> reactedEntities;

    /**
     * The Is dead.
     */
    boolean isDead;

    /**
     * Instantiates a new Bullet effect.
     */
    BulletEffect() {
        reactedEntities = new ArrayList<>();

        isDead = false;
    }

    /**
     * React.
     *
     * @param entity the entity
     */
    public void react(Entity entity) {
        isDead = false;
    }

    /**
     * Reset.
     */
    public abstract void reset();

    /**
     * Update.
     *
     * @param deltaTime the delta time
     */
    public abstract void update(Time deltaTime);

    /**
     * Is dead boolean.
     *
     * @return the boolean
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Sets dead.
     *
     * @param dead the dead
     */
    public void setDead(boolean dead) {
        isDead = dead;
    }
}
