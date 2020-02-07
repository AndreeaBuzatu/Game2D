package uk.ac.lancaster.scc210.game.level;

import uk.ac.lancaster.scc210.engine.ecs.Entity;

import java.util.List;

public class LevelStage {
    private final List<LevelWave> waves;

    private final List<Entity> stationaryEntities;

    public LevelStage(List<LevelWave> waves, List<Entity> stationaryEntities) {
        this.waves = waves;
        this.stationaryEntities = stationaryEntities;
    }

    public boolean complete() {
        // All are complete
        return waves.parallelStream().allMatch(LevelWave::complete) && stationaryEntities.isEmpty();
    }

    public List<LevelWave> getWaves() {
        return waves;
    }

    public List<Entity> getStationaryEntities() {
        return stationaryEntities;
    }

    public void removeStationaryEntity(Entity entity) {
        stationaryEntities.remove(entity);
    }
}
