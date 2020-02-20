package uk.ac.lancaster.scc210.game.content;

import uk.ac.lancaster.scc210.engine.content.ContentManager;
import uk.ac.lancaster.scc210.game.level.Level;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelManager extends ContentManager<Level> {
    // As a HashMap can't ensure ordering, use a queue to enter levels as they are inserted, to ensure ordering
    private Queue<Level> levelQueue;

    public LevelManager(List<Level> levels) {
        super(null);

        levelQueue = new LinkedList<>();

        levelQueue.addAll(levels);

        for (Level level : levels) {
            put(level.getName(), level);
        }
    }

    public Queue<Level> getLevelQueue() {
        return levelQueue;
    }

    public Iterator<Level> getIterator() {
        return values().iterator();
    }
}

