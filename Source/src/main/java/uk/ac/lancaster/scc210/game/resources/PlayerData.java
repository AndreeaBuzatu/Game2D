package uk.ac.lancaster.scc210.game.resources;

import uk.ac.lancaster.scc210.engine.resources.deserialise.Serialised;
import uk.ac.lancaster.scc210.engine.service.Service;

/**
 * The type Player data.
 */
public class PlayerData implements Serialised, Service {
    private final int startingLives;

    private String unlockedLevel;

    private int score, lives;

    /**
     * Instantiates a new Player data.
     *
     * @param unlockedLevel the unlocked level
     * @param score         the score
     * @param lives         the lives
     */
    PlayerData(String unlockedLevel, int score, int lives) {
        this.unlockedLevel = unlockedLevel;
        this.score = score;
        this.lives = lives;

        this.startingLives = lives;
    }

    /**
     * Gets unlocked level.
     *
     * @return the unlocked level
     */
    public String getUnlockedLevel() {
        return unlockedLevel;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
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
     * Sets unlocked level.
     *
     * @param unlockedLevel the unlocked level
     */
    public void setUnlockedLevel(String unlockedLevel) {
        this.unlockedLevel = unlockedLevel;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(int score) {
        this.score = score;
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
     * Reset lives.
     */
    public void resetLives() {
        lives = startingLives;
    }
}
