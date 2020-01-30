package uk.ac.lancaster.scc210.game.level;

import uk.ac.lancaster.scc210.engine.resources.deserialise.Serialised;

import java.util.List;

public class Level implements Serialised {
    private final List<LevelStage> stages;

    private final int currentStage;

    public Level(List<LevelStage> stages) {
        this.stages = stages;

        currentStage = 0;
    }

    public List<LevelStage> getStages() {
        return stages;
    }

    public LevelStage getCurrentStage() {
        return stages.get(currentStage);
    }

    /*
    void createWaves() {
        stages.get(currentStage).getWaves()
    }
     */
}
