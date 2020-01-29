package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;
import uk.ac.lancaster.scc210.game.waves.Wave;

public class WaveComponent implements Component {
    private Wave wave;

    public WaveComponent(Wave wave) {
        this.wave = wave;
    }

    public Wave getWave() {
        return wave;
    }
}
