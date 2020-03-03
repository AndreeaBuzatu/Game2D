package uk.ac.lancaster.scc210.game.states;

import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.game.content.LevelManager;
import uk.ac.lancaster.scc210.game.level.Level;

import java.util.List;

public class LevelLoader {
    private List<Level> totalLevels;

    private LevelManager levelManager;

    private World world;

    private Level currentLevel;
    
    private int currentUnlocked;

    LevelLoader(World world, LevelManager levelManager) {
        this.world = world;
        this.levelManager = levelManager;

        totalLevels = levelManager.getLevelList();

        currentUnlocked = -1;
    }

    Level loadLevel(String levelName) {
        world.clear();

        currentLevel = levelManager.get(levelName);
        
        currentUnlocked = levelManager.indexOf(levelName);

        return currentLevel;
    }

    Level nextLevel() {
        if (currentLevel == null || lastLevel()) {
            return null;
        }

        currentUnlocked++;

        return levelManager.getLevelList().get(currentUnlocked);
    }

    boolean levelNotFound() {
        return currentUnlocked < 0;
    }

    boolean lastLevel() {
        return currentUnlocked >= totalLevels.size() - 1;
    }
}
