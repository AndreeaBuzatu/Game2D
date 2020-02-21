package uk.ac.lancaster.scc210.game.ecs.component;

import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Component;
import uk.ac.lancaster.scc210.game.items.ItemEffect;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PlayerComponent implements Component {
    private Set<ItemEffect> currentEffects;

    private Vector2f spawnPoint;

    private int currentScore;

    public PlayerComponent() {
        currentEffects = new HashSet<>();

        currentScore = 0;
    }

    public Set<ItemEffect> getCurrentEffects() {
        return currentEffects;
    }

    public void setCurrentEffects(Set<ItemEffect> currentEffects) {
        this.currentEffects.addAll(currentEffects);
    }

    public void changeScore(int score) {
        this.currentScore += score;
    }

    public int getScore() {
        return currentScore;
    }

    public void setScore(int score) {
        this.currentScore = score;
    }

    public void setSpawnPoint(Vector2f spawnPoint) {
        this.spawnPoint = spawnPoint;
    }

    public Vector2f getSpawnPoint() {
        return spawnPoint;
    }
}
