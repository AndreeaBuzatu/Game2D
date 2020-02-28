package uk.ac.lancaster.scc210.game.resources;

import uk.ac.lancaster.scc210.engine.resources.deserialise.Serialised;
import uk.ac.lancaster.scc210.engine.service.Service;

public class PlayerData implements Serialised, Service {
    private final String unlockedLevel;

    private final int score, lives;

    PlayerData(String unlockedLevel, int score, int lives) {
        this.unlockedLevel = unlockedLevel;
        this.score = score;
        this.lives = lives;
    }

    public String getUnlockedLevel() {
        return unlockedLevel;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }
}
