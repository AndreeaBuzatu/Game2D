package uk.ac.lancaster.scc210.game.states;

import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.game.content.LevelManager;
import uk.ac.lancaster.scc210.game.level.Level;

import java.util.List;

/**
 * The type Level loader.
 */
public class LevelLoader {
    private List<Level> totalLevels;

    private LevelManager levelManager;

    private World world;

    private Level currentLevel;
    
    private int currentUnlocked;

    /**
     * Instantiates a new Level loader.
     *
     * @param world        the world
     * @param levelManager the level manager
     */
    LevelLoader(World world, LevelManager levelManager) {
        this.world = world;
        this.levelManager = levelManager;

        totalLevels = levelManager.getLevelList();

        currentUnlocked = -1;
    }

    /**
     * Load level level.
     *
     * @param levelName the level name
     * @return the level
     */
    Level loadLevel(String levelName) {
        world.clear();

        currentLevel = levelManager.get(levelName);
        
        currentUnlocked = levelManager.indexOf(levelName);

        return currentLevel;
    }

    /**
     * Next level level.
     *
     * @return the level
     */
    Level nextLevel() {
        if (currentLevel == null || lastLevel()) {
            return null;
        }

        currentUnlocked++;

        return levelManager.getLevelList().get(currentUnlocked);
    }

    /**
     * Level not found boolean.
     *
     * @return the boolean
     */
    boolean levelNotFound() {
        return currentUnlocked < 0;
    }

    /**
     * Last level boolean.
     *
     * @return the boolean
     */
    boolean lastLevel() {
        return currentUnlocked >= totalLevels.size() - 1;
    }
}
