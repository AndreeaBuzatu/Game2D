package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;
import uk.ac.lancaster.scc210.game.items.ItemEffect;

import java.util.Set;

public class PlayerComponent implements Component {
    private Set<ItemEffect> currentEffects;

    public Set<ItemEffect> getCurrentEffects() {
        return currentEffects;
    }

    public void setCurrentEffects(Set<ItemEffect> currentEffects) {
        this.currentEffects = currentEffects;
    }
}
