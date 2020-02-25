package uk.ac.lancaster.scc210.game.resources;

import uk.ac.lancaster.scc210.engine.resources.deserialise.Serialised;

public class HighScore implements Serialised {
    private final String playerName;

    private final int score;

    HighScore(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }
}
