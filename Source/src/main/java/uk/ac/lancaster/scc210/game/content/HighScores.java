package uk.ac.lancaster.scc210.game.content;

import uk.ac.lancaster.scc210.engine.service.Service;
import uk.ac.lancaster.scc210.game.resources.HighScore;

import java.util.List;

/**
 * The type High scores.
 */
public class HighScores implements Service {
    private final List<HighScore> highScores;

    /**
     * Instantiates a new High scores.
     *
     * @param highScores the high scores
     */
    public HighScores(List<HighScore> highScores) {
        this.highScores = highScores;
    }

    /**
     * Gets high scores.
     *
     * @return the high scores
     */
    public List<HighScore> getHighScores() {
        return highScores;
    }
}
