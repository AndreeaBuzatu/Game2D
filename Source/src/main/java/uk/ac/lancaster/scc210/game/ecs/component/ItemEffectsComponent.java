package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;
import uk.ac.lancaster.scc210.game.items.ItemEffect;

import java.util.Set;

public class ItemEffectsComponent implements Component {
    private final Set<ItemEffect> itemEffects;

    public ItemEffectsComponent(Set<ItemEffect> itemEffects) {
        this.itemEffects = itemEffects;
    }

    public Set<ItemEffect> getItemEffects() {
        return itemEffects;
    }
}
