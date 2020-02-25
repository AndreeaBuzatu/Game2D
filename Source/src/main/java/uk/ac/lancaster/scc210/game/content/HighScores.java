package uk.ac.lancaster.scc210.game.content;

import uk.ac.lancaster.scc210.engine.service.Service;
import uk.ac.lancaster.scc210.game.resources.HighScore;

import java.util.List;

public class HighScores implements Service {
    private final List<HighScore> highScores;

    public HighScores(List<HighScore> highScores) {
        this.highScores = highScores;
    }

    public List<HighScore> getHighScores() {
        return highScores;
    }
}
