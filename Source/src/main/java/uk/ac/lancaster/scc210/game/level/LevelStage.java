package uk.ac.lancaster.scc210.game.level;

import java.util.List;

public class LevelStage {
    private final List<LevelWave> waves;

    private final LevelWave currentWave;

    public LevelStage(List<LevelWave> waves) {
        this.waves = waves;

        currentWave = waves.get(0);
    }

    boolean complete() {
        // All the waves have been completed
        return waves.parallelStream().distinct().count() <= 1;
    }

    public List<LevelWave> getWaves() {
        return waves;
    }

    LevelWave getCurrentWave() {
        return currentWave;
    }
}
