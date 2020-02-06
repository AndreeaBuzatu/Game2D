package uk.ac.lancaster.scc210.game.prototypes;

import org.jsfml.graphics.Sprite;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.prototypes.Prototype;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

public class ItemPrototype implements Prototype {
    private final TextureManager textureManager;

    public ItemPrototype(TextureManager textureManager) {
        this.textureManager = textureManager;
    }

    public Entity create() {
        final SpriteComponent spriteComponent = new SpriteComponent(new Sprite(textureManager.get("spritesheet.png"))); //TODO: what goes here?

        return World.createEntity(spriteComponent);
    }
}
