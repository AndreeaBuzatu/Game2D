package uk.ac.lancaster.scc210.game.level;

import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.game.dialogue.Line;
import uk.ac.lancaster.scc210.game.ecs.component.FlashComponent;
import uk.ac.lancaster.scc210.game.ecs.component.LivesComponent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The type Level stage.
 */
public class LevelStage {
    private final List<LevelWave> waves;

    private final List<Line> lines;

    private final Set<Entity> stationaryEntities, startingStationary;

    private boolean stationarySpawned;

    /**
     * Instantiates a new Level stage.
     *
     * @param waves              the waves
     * @param stationaryEntities the stationary entities
     */
    public LevelStage(List<Line> lines, List<LevelWave> waves, Set<Entity> stationaryEntities) {
        this.lines = lines;
        this.waves = waves;
        this.stationaryEntities = stationaryEntities;

        startingStationary = new HashSet<>();

        // Maintain a list of stationary entities which can be reused later
        startingStationary.addAll(stationaryEntities);

        stationarySpawned = false;
    }

    /**
     * Complete boolean.
     *
     * @return the boolean
     */
    public boolean complete() {
        // All are complete
        return waves.stream().allMatch(LevelWave::complete) && stationaryEntities.isEmpty();
    }

    /**
     * Update.
     *
     * @param world     the world
     * @param deltaTime the delta time
     */
    public void update(World world, Time deltaTime) {
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

            if (newShip != null && !world.getEntities().contains(newShip)) {
                world.addEntity(newShip);
            }

            wave.getWave().update(wave.getEntities(), deltaTime);
        }
    }

    /**
     * Reset.
     */
    void reset() {
        waves.forEach(LevelWave::reset);

        stationaryEntities.addAll(startingStationary);

        stationaryEntities.forEach(stationaryEntity -> {
            LivesComponent livesComponent = (LivesComponent) stationaryEntity.findComponent(LivesComponent.class);

            FlashComponent flashComponent = (FlashComponent) stationaryEntity.findComponent(FlashComponent.class);

            livesComponent.resurrect();

            flashComponent.resetToTexture();
        });

        stationarySpawned = false;
    }

    /**
     * Gets stationary entities.
     *
     * @return the stationary entities
     */
    public Set<Entity> getStationaryEntities() {
        return stationaryEntities;
    }

    public List<Line> getLines() {
        return lines;
    }

    /**
     * Has spawned boolean.
     *
     * @return the boolean
     */
    public boolean hasSpawned() {
        return waves.stream().allMatch(LevelWave::complete) && stationarySpawned;
    }

    /**
     * Remove stationary entity.
     *
     * @param entity the entity
     */
    public void removeStationaryEntity(Entity entity) {
        stationaryEntities.remove(entity);
    }
}
