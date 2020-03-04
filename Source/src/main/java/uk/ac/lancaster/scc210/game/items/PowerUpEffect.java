package uk.ac.lancaster.scc210.game.items;

import org.jsfml.system.Time;

/**
 * The type Power up effect.
 */
public abstract class PowerUpEffect extends ItemEffect {
    @Override
    public void update(Time deltaTime) {
        isDead = true;
    }
}
