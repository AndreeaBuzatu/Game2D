package uk.ac.lancaster.scc210.game.prototypes;

import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.prototypes.Prototype;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

public class ItemPrototype implements Prototype {
    private final Texture texture;

    public ItemPrototype(Texture texture) {
        this.texture = texture;
    }

    public Entity create() {
        final SpriteComponent spriteComponent = new SpriteComponent(new Sprite(texture)); //TODO: what goes here?

        return World.createEntity(spriteComponent);
    }
}
