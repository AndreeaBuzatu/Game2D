package uk.ac.lancaster.scc210.game.content;

import uk.ac.lancaster.scc210.engine.content.ContentManager;
import uk.ac.lancaster.scc210.game.level.Level;

import java.util.List;

public class LevelManager extends ContentManager<Level> {
    public LevelManager(List<Level> levels) {
        super(null);

        int i = 0;

        for (Level level : levels) {
            put(String.valueOf(i), level);

            i++;
        }

        /*
        for (Playing level : levels) {
            for (LevelStage stage : level.getStages()) {
                for (LevelWave wave : stage.getWaves()) {
                    wave.
                }
            }
        }
         */
    }
}
