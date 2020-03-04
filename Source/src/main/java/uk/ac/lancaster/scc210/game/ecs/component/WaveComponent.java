package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;
import uk.ac.lancaster.scc210.game.level.LevelWave;

/**
 * The type Wave component.
 */
public class WaveComponent implements Component {
    private final LevelWave wave;

    /**
     * Instantiates a new Wave component.
     *
     * @param wave the wave
     */
    public WaveComponent(LevelWave wave) {
        this.wave = wave;
    }

    /**
     * Gets wave.
     *
     * @return the wave
     */
    public LevelWave getWave() {
        return wave;
    }
}
