package uk.ac.lancaster.scc210.game.level;

import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;

import java.util.List;

public class LevelStage {
    private final List<LevelWave> waves;

    private final List<Entity> stationaryEntities;

    private boolean stationarySpawned;

    public LevelStage(List<LevelWave> waves, List<Entity> stationaryEntities) {
        this.waves = waves;
        this.stationaryEntities = stationaryEntities;

        stationarySpawned = false;
    }

    public boolean complete() {
        // All are complete
        return waves.parallelStream().allMatch(LevelWave::complete) && stationaryEntities.isEmpty();
    }

    public void spawn(World world, Time deltaTime) {
        // Prevent stationary entities from being re-spawned
        if (!stationarySpawned) {
            world.addEntities(stationaryEntities);

            stationarySpawned = true;
        }

        for (LevelWave wave : waves) {
            Entity newShip = wave.spawnNew(deltaTime);

            if (newShip != null) {
                world.addEntity(newShip);
            }

            wave.getWave().update(wave.getEntities(), deltaTime);
        }
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
