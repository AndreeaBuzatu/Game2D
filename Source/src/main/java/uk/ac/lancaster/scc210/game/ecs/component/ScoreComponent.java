package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;

/**
 * The type Score component.
 */
public class ScoreComponent implements Component {
    private final int score;

    /**
     * Instantiates a new Score component.
     *
     * @param score the score
     */
    public ScoreComponent(int score) {
        this.score = score;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }
}
