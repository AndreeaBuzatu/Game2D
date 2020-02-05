package uk.ac.lancaster.scc210.game.bullets.patterns;

import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;
import uk.ac.lancaster.scc210.game.pooling.BulletPool;

public abstract class Pattern {
    Entity[] bullets;

    SpriteComponent spaceShipSprite, bulletSprite;

    private Entity spaceSphip;

    Pool pool;

    World world;


    Pattern(World world, Entity spaceShip, Entity[] bullets)
    {
        this.bullets = bullets;
        this.spaceSphip = spaceShip;
        this.world = world;

        pool = world.getPool(BulletPool.class);

        for(int i = 0; i < 8; i++)
        {
            bullets[i] = pool.borrowEntity();
        }

        spaceShipSprite = (SpriteComponent) spaceShip.findComponent(SpriteComponent.class);

        bulletSprite = (SpriteComponent) bullets[0].findComponent(SpriteComponent.class);
    }

    abstract void fire();


}
