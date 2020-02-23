package uk.ac.lancaster.scc210.game.items;

import org.jsfml.system.Time;

public abstract class TimedItemEffect extends ItemEffect {
    private final Time duration;

    private Time elapsedTime;

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
