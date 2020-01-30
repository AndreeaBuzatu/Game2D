package uk.ac.lancaster.scc210.game.level;

import java.util.List;

public class LevelStage {
    private final List<LevelWave> waves;

    public LevelStage(List<LevelWave> waves) {
        this.waves = waves;
    }

    boolean complete() {
        // All the waves have been completed
        return waves.parallelStream().distinct().count() <= 1;
    }

    public List<LevelWave> getWaves() {
        return waves;
    }
}
