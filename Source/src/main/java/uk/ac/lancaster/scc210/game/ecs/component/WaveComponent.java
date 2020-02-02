package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;
import uk.ac.lancaster.scc210.game.level.LevelWave;

public class WaveComponent implements Component {
    private final LevelWave wave;

    public WaveComponent(LevelWave wave) {
        this.wave = wave;
    }

    public LevelWave getWave() {
        return wave;
    }
}
