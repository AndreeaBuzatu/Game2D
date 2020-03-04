package uk.ac.lancaster.scc210.game.bullets.effects;

import org.jsfml.system.Time;

/**
 * The type Degrading lives effect.
 */
public class DegradingLivesEffect extends DamageEffect {
    /**
     * Instantiates a new Degrading lives effect.
     *
     * @param degradeTime the degrade time
     */
    public DegradingLivesEffect(Time degradeTime) {
        super((int) degradeTime.asSeconds());
    }
}
