package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;
import uk.ac.lancaster.scc210.game.items.ItemEffect;

import java.util.Set;

/**
 * The type Item effects component.
 */
public class ItemEffectsComponent implements Component {
    private final Set<ItemEffect> itemEffects;

    /**
     * Instantiates a new Item effects component.
     *
     * @param itemEffects the item effects
     */
    public ItemEffectsComponent(Set<ItemEffect> itemEffects) {
        this.itemEffects = itemEffects;
    }

    /**
     * Gets item effects.
     *
     * @return the item effects
     */
    public Set<ItemEffect> getItemEffects() {
        return itemEffects;
    }
}
