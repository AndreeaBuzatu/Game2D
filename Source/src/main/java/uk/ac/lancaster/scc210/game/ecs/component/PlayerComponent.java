package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;
import uk.ac.lancaster.scc210.game.items.ItemEffect;

import java.util.HashSet;
import java.util.Set;

public class PlayerComponent implements Component {
    private Set<ItemEffect> currentEffects;

    private int currentScore;

    public PlayerComponent() {
        currentEffects = new HashSet<>();

        currentScore = 0;
    }

    public Set<ItemEffect> getCurrentEffects() {
        return currentEffects;
    }

    public void setCurrentEffects(Set<ItemEffect> currentEffects) {
        this.currentEffects = currentEffects;
    }

    public void changeScore(int score) {
        this.currentScore += score;
    }

    public int getScore() {
        return currentScore;
    }
}
