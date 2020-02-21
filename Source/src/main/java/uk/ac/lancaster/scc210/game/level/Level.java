package uk.ac.lancaster.scc210.game.level;

import uk.ac.lancaster.scc210.engine.resources.deserialise.Serialised;
import uk.ac.lancaster.scc210.game.dialogue.Line;

import java.util.Iterator;
import java.util.List;

public class Level implements Serialised {
    private final List<LevelStage> stages;

    private final List<Line> lines;

    private final Iterator<LevelStage> stageIterator;

    private LevelStage currentStage;

    public Level(List<LevelStage> stages, List<Line> lines) {
        this.stages = stages;
        this.lines = lines;

        stageIterator = stages.iterator();

        currentStage = stageIterator.next();
    }

    public LevelStage changeStage() {
        if (stageIterator.hasNext()) {
            currentStage = stageIterator.next();

            return currentStage;

        } else {
            return null;
        }
    }

    public void reset() {
        //stages.forEach(LevelStage::reset);
        currentStage.reset();
    }

    public boolean complete() {

        return stages.stream().allMatch(LevelStage::complete);
    }

    public LevelStage getCurrentStage() {
        return currentStage;
    }

    public List<Line> getLines() {
        return lines;
    }
}
