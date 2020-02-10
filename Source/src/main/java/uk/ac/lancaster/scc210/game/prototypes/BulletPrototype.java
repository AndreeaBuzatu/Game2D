package uk.ac.lancaster.scc210.game.prototypes;

import org.jsfml.graphics.Sprite;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.prototypes.Prototype;
import uk.ac.lancaster.scc210.game.ecs.component.SpeedComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

public class BulletPrototype implements Prototype {
    private final TextureManager textureManager;

    private final String texture;

    private final int speed;

    public BulletPrototype(TextureManager textureManager, String texture, int speed) {
        this.textureManager = textureManager;
        this.texture = texture;
        this.speed = speed;
    }

    public Entity create() {
        final SpriteComponent spriteComponent = new SpriteComponent(new Sprite(textureManager.get(texture)));

        final SpeedComponent speedComponent = new SpeedComponent(speed);

        return World.createEntity(spriteComponent, speedComponent);
    }
}
