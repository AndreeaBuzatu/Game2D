package uk.ac.lancaster.scc210.game.bullets.effects;

import org.jsfml.system.Time;

/**
 * The type Timed bullet effect.
 */
public abstract class TimedBulletEffect extends BulletEffect {
    private final Time duration;

    private Time elapsedTime;

    /**
     * Instantiates a new Timed bullet effect.
     *
     * @param duration the duration
     */
    TimedBulletEffect(Time duration) {
        this.duration = duration;

        elapsedTime = Time.ZERO;

        isDead = false;
    }

    public void update(Time deltaTime) {
        elapsedTime = Time.add(elapsedTime, deltaTime);

        if (elapsedTime.asSeconds() > duration.asSeconds()) {
            elapsedTime = Time.ZERO;

            isDead = true;
        }
    }
}
