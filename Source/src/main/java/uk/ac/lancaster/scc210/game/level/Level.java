package uk.ac.lancaster.scc210.game.level;

import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.resources.deserialise.Serialised;

import java.util.List;

public class Level implements Serialised {
    private final List<LevelStage> stages;

    private final LevelStage currentStage;

    public Level(List<LevelStage> stages) {
        this.stages = stages;

        currentStage = stages.get(0);
    }

    public List<LevelStage> getStages() {
        return stages;
    }

    public LevelStage getCurrentStage() {
        return currentStage;
    }

    public void update(World world) {
        LevelWave currentWave = currentStage.getCurrentWave();

        if (!currentWave.allSpawned()) {
            Entity newShip = currentWave.spawnNew();

            if (newShip != null) {
                world.addEntity(newShip);
            }
        }

        currentWave.getWave().update(currentWave.getEntities());
    }
}
