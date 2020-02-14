package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;

public class ScoreComponent implements Component {
    private final int score;

    public ScoreComponent(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
