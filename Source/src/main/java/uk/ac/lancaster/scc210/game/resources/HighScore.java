package uk.ac.lancaster.scc210.game.resources;

import uk.ac.lancaster.scc210.engine.resources.deserialise.Serialised;

/**
 * The type High score.
 */
public class HighScore implements Serialised {
    private String playerName;

    private int score;

    /**
     * Instantiates a new High score.
     *
     * @param playerName the player name
     * @param score      the score
     */
    public HighScore(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    /**
     * Gets player name.
     *
     * @return the player name
     */
    public String getPlayerName() {
        return playerName;
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
     * Sets player name.
     *
     * @param playerName the player name
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(int score) {
        this.score = score;
    }
}
