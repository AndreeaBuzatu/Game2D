package uk.ac.lancaster.scc210.game.prototypes;

import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.prototypes.Prototype;
import uk.ac.lancaster.scc210.game.ecs.component.ItemEffectsComponent;
import uk.ac.lancaster.scc210.game.ecs.component.OrientatedBoxComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;
import uk.ac.lancaster.scc210.game.ecs.component.TransformableComponent;
import uk.ac.lancaster.scc210.game.items.ItemEffect;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Item prototype.
 */
public class ItemPrototype implements Prototype {
    private final Texture texture;

    private final Set<ItemEffect> itemEffects;

    /**
     * Instantiates a new Item prototype.
     *
     * @param texture     the texture
     * @param itemEffects the item effects
     */
    public ItemPrototype(Texture texture, ItemEffect... itemEffects) {
        this.texture = texture;
        this.itemEffects = new HashSet<>(Arrays.asList(itemEffects));
    }

    public Entity create() {
        final Sprite sprite = new Sprite(texture);

        final SpriteComponent spriteComponent = new SpriteComponent(sprite);

        final ItemEffectsComponent itemEffectsComponent = new ItemEffectsComponent(itemEffects);

        final TransformableComponent transformableComponent = new TransformableComponent(sprite);

        final OrientatedBoxComponent orientatedBoxComponent = new OrientatedBoxComponent(sprite);

        return World.createEntity(spriteComponent, itemEffectsComponent, orientatedBoxComponent, transformableComponent);
    }
}
