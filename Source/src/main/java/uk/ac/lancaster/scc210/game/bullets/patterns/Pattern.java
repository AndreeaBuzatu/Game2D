package uk.ac.lancaster.scc210.game.bullets.patterns;

import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

public abstract class Pattern {
    final Entity[] bullets;

    final Vector2f[] coords;

    final Pool pool;

    final Sprite spaceShipSprite;

    final String bulletName;

    Pattern(Pool pool, Entity spaceShip, Entity[] bullets, String bulletName) {
        this.pool = pool;
        this.bullets = bullets;
        this.bulletName = bulletName;

        coords = new Vector2f[bullets.length];

        spaceShipSprite = ((SpriteComponent) spaceShip.findComponent(SpriteComponent.class)).getSprite();
    }

    public abstract Entity[] create();

    abstract void position(Sprite bulletSprite);
}
