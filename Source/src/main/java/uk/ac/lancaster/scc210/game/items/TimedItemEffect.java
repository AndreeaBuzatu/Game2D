package uk.ac.lancaster.scc210.game.items;

import org.jsfml.system.Time;

/**
 * The type Timed item effect.
 */
public abstract class TimedItemEffect extends ItemEffect {
    /**
     * The Duration.
     */
    final Time duration;

    private Time elapsedTime;

    /**
     * Instantiates a new Timed item effect.
     *
     * @param duration the duration
     */
    protected TimedItemEffect(Time duration) {
        this.duration = duration;

        elapsedTime = Time.ZERO;
    }

    /**
     * Manages how long the item should be available to the player
     *
     * @param deltaTime delta time of the game
     */
    @Override
    public void update(Time deltaTime) {
        elapsedTime = Time.add(elapsedTime, deltaTime);

        if (elapsedTime.asSeconds() > duration.asSeconds()) {
            elapsedTime = Time.ZERO;

            isDead = true;
        }
    }
}
