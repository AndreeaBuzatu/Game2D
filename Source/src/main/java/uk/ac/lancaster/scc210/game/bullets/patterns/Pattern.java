package uk.ac.lancaster.scc210.game.bullets.patterns;

import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;
import uk.ac.lancaster.scc210.game.pooling.BulletPool;

abstract class Pattern {
    private final Pool pool;

    final Entity[] bullets;

    final Vector2f[] coords;

    final SpriteComponent spaceShipSprite, bulletSprite;

    final World world;

    Pattern(World world, Entity spaceShip, Entity[] bullets) {
        this.bullets = bullets;
        this.world = world;

        pool = world.getPool(BulletPool.class);

        coords = new Vector2f[bullets.length];

        for (int i = 0; i < bullets.length; i++) {
            bullets[i] = pool.borrowEntity();
        }

        spaceShipSprite = (SpriteComponent) spaceShip.findComponent(SpriteComponent.class);

        bulletSprite = (SpriteComponent) bullets[0].findComponent(SpriteComponent.class);
    }

    public abstract void fire();
}
