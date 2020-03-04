package uk.ac.lancaster.scc210.game.content;

import uk.ac.lancaster.scc210.engine.content.ContentManager;
import uk.ac.lancaster.scc210.game.level.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Level manager.
 */
public class LevelManager extends ContentManager<Level> {
    // As a HashMap can't ensure ordering, use a queue to enter levels as they are inserted, to ensure ordering
    private final List<Level> levelList;

    /**
     * Instantiates a new Level manager.
     *
     * @param levels the levels
     */
    public LevelManager(List<Level> levels) {
        super(null);

        levelList = new ArrayList<>();

        levelList.addAll(levels);

        for (Level level : levels) {
            put(level.getName(), level);
        }
    }

    /**
     * Gets level list.
     *
     * @return the level list
     */
    public List<Level> getLevelList() {
        return levelList;
    }

    /**
     * Gets unlocked.
     *
     * @param levelName the level name
     * @return the unlocked
     */
    public List<Level> getUnlocked(String levelName) {
        List<Level> levels = new ArrayList<>();

        // + 1 indicates the previous levels and the currently unlocked levels
        for (int i = 0; i < indexOf(levelName) + 1; i++) {
            levels.add(levelList.get(i));
        }

        return levels;
    }

    /**
     * Index of int.
     *
     * @param levelName the level name
     * @return the int
     */
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

