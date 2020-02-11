package uk.ac.lancaster.scc210.game.prototypes;

import org.jsfml.graphics.Sprite;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.prototypes.Prototype;
import uk.ac.lancaster.scc210.game.ecs.component.OrientatedBoxComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpeedComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

public class BulletPrototype implements Prototype {
    private final TextureManager textureManager;

    public BulletPrototype(TextureManager textureManager) {
        this.textureManager = textureManager;
    }

    public Entity create() {
        final SpriteComponent spriteComponent = new SpriteComponent(new Sprite(textureManager.get("bullets.png:example_bullet")));

        final SpeedComponent speedComponent = new SpeedComponent(5);

        final OrientatedBoxComponent orientatedBoxComponent = new OrientatedBoxComponent(spriteComponent.getSprite());

        return World.createEntity(spriteComponent, speedComponent, orientatedBoxComponent);
    }
}
