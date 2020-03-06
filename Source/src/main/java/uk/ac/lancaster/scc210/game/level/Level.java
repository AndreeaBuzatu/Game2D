package uk.ac.lancaster.scc210.game.level;

import uk.ac.lancaster.scc210.engine.resources.deserialise.Serialised;

import java.util.Iterator;
import java.util.List;

/**
 * The type Level.
 */
public class Level implements Serialised {
    private final List<LevelStage> stages;

    private Iterator<LevelStage> stageIterator;

    private final String name;

    private LevelStage currentStage;

    /**
     * Instantiates a new Level.
     *
     * @param name   the name
     * @param stages the stages
     */
    public Level(String name, List<LevelStage> stages) {
        this.name = name;
        this.stages = stages;

        stageIterator = stages.iterator();

        currentStage = stageIterator.next();
    }

    /**
     * Change stage level stage.
     *
     * @return the level stage
     */
    public LevelStage changeStage() {
        if (stageIterator.hasNext()) {
            currentStage = stageIterator.next();

            return currentStage;

        } else {
            return null;
        }
    }

    /**
     * Restart level.
     */
    public void restartLevel() {
        stages.forEach(LevelStage::reset);

        currentStage = stages.get(0);

        stageIterator = stages.iterator();
    }

    /**
     * Reset.
     */
    public void reset() {
        currentStage.reset();
    }

    /**
     * Complete boolean.
     *
     * @return the boolean
     */
    public boolean complete() {
        return stages.stream().allMatch(LevelStage::complete);
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets current stage.
     *
     * @return the current stage
     */
    public LevelStage getCurrentStage() {
        return currentStage;
    }
}
