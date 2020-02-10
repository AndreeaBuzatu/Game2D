package uk.ac.lancaster.scc210.game.prototypes;

import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.prototypes.Prototype;
import uk.ac.lancaster.scc210.game.ecs.component.ItemEffectsComponent;
import uk.ac.lancaster.scc210.game.ecs.component.OrientatedBoxComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;
import uk.ac.lancaster.scc210.game.items.ItemEffect;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ItemPrototype implements Prototype {
    private final Texture texture;

    private final Set<ItemEffect> itemEffects;

    public ItemPrototype(Texture texture, ItemEffect... itemEffects) {
        this.texture = texture;
        this.itemEffects = new HashSet<>(Arrays.asList(itemEffects));
    }

    public Entity create() {
        final SpriteComponent spriteComponent = new SpriteComponent(new Sprite(texture));

        final ItemEffectsComponent itemEffectsComponent = new ItemEffectsComponent(itemEffects);

        final OrientatedBoxComponent orientatedBoxComponent = new OrientatedBoxComponent(spriteComponent.getSprite());

        return World.createEntity(spriteComponent, itemEffectsComponent, orientatedBoxComponent);
    }
}
