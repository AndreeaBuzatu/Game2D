package uk.ac.lancaster.scc210.game.content;

import uk.ac.lancaster.scc210.engine.content.ContentManager;
import uk.ac.lancaster.scc210.game.level.Level;

import java.util.ArrayList;
import java.util.List;

public class LevelManager extends ContentManager<Level> {
    // As a HashMap can't ensure ordering, use a queue to enter levels as they are inserted, to ensure ordering
    private List<Level> levelList;

    public LevelManager(List<Level> levels) {
        super(null);

        levelList = new ArrayList<>();

        levelList.addAll(levels);

        for (Level level : levels) {
            put(level.getName(), level);
        }
    }

    public List<Level> getLevelList() {
        return levelList;
    }

    public List<Level> getUnlocked(String levelName) {
        List<Level> levels = new ArrayList<>();

        // + 1 indicates the previous levels and the currently unlocked levels
        for (int i = 0; i < indexOf(levelName) + 1; i++) {
            levels.add(levelList.get(i));
        }

        return levels;
    }

    public int indexOf(String levelName) {
        for (int i = 0; i < levelList.size(); i++) {
            if (levelList.get(i).getName().equals(levelName)) {
                return i;
            }
        }

        // TODO: Change this
        return -1;
    }
}

