package uk.ac.lancaster.scc210.game.level;

import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.game.ecs.component.FlashComponent;
import uk.ac.lancaster.scc210.game.ecs.component.LivesComponent;

import java.util.List;
import java.util.Set;

public class LevelStage {
    private final List<LevelWave> waves;

    private final Set<Entity> stationaryEntities;

    private boolean stationarySpawned;

    public LevelStage(List<LevelWave> waves, Set<Entity> stationaryEntities) {
        this.waves = waves;

        this.stationaryEntities = stationaryEntities;

        stationarySpawned = false;
    }

    public boolean complete() {
        // All are complete
        return waves.stream().allMatch(LevelWave::complete) && stationaryEntities.isEmpty();
    }

    public void spawn(World world, Time deltaTime) {
        if (hasSpawned()) {
            return;
        }
        
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

            world.addEntities(wave.getEntities());

            wave.getWave().update(wave.getEntities(), deltaTime);
        }
    }

    void reset() {
        waves.forEach(LevelWave::reset);

        stationaryEntities.forEach(stationaryEntity -> {
            LivesComponent livesComponent = (LivesComponent) stationaryEntity.findComponent(LivesComponent.class);

            FlashComponent flashComponent = (FlashComponent) stationaryEntity.findComponent(FlashComponent.class);

            livesComponent.resurrect();

            flashComponent.resetToTexture();
        });

        stationarySpawned = false;
    }

    public Set<Entity> getStationaryEntities() {
        return stationaryEntities;
    }

    public boolean hasSpawned() {
        return waves.isEmpty() && stationarySpawned;
    }

    public void removeStationaryEntity(Entity entity) {
        stationaryEntities.remove(entity);
    }
}
