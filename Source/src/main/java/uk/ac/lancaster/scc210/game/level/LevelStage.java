package uk.ac.lancaster.scc210.game.level;

import java.util.List;

public class LevelStage {
    private final List<LevelWave> waves;

    public LevelStage(List<LevelWave> waves) {
        this.waves = waves;
    }

    public boolean complete() {
        // All are complete
        return waves.parallelStream().allMatch(LevelWave::complete);
    }

    public List<LevelWave> getWaves() {
        return waves;
    }
}
