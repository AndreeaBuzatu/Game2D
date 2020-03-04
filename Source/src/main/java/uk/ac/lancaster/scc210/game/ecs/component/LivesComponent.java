package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;

/**
 * The type Lives component.
 */
public class LivesComponent implements Component {
    private int startingLives;

    private int lives;

    /**
     * Instantiates a new Lives component.
     *
     * @param lives the lives
     */
    public LivesComponent(int lives) {
        this.lives = lives;

        startingLives = lives;
    }

    /**
     * Gets lives.
     *
     * @return the lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * Sets lives.
     *
     * @param lives the lives
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Gets starting lives.
     *
     * @return the starting lives
     */
    public int getStartingLives() {
        return startingLives;
    }

    /**
     * Sets starting lives.
     *
     * @param startingLives the starting lives
     */
    public void setStartingLives(int startingLives) {
        this.startingLives = startingLives;
    }

    /**
     * Is dead boolean.
     *
     * @return the boolean
     */
    public boolean isDead()
    {
        return lives < 1;
    }

    /**
     * Resurrect.
     */
    public void resurrect() {
        lives = startingLives;
    }

    /**
     * Kill.
     */
    public void kill() {
        lives = 0;
    }
}
