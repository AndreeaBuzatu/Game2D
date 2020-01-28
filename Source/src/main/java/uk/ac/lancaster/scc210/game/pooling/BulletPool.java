package uk.ac.lancaster.scc210.game.pooling;

import org.jsfml.graphics.Sprite;
import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.component.PooledComponent;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.engine.service.ServiceProvider;
import uk.ac.lancaster.scc210.game.ecs.component.SpeedComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

/**
 * Pool which stores bullets.
 */
public class BulletPool extends Pool {
    // Who cares if it's static? I don't
    private static final int INITIAL_BULLETS = 100;

    private final ServiceProvider serviceProvider;

    /**
     * Instantiate a BulletPool
     *
     * @param serviceProvider used to access the PoolService
     */
    public BulletPool(ServiceProvider serviceProvider) {
        super(INITIAL_BULLETS);

        this.serviceProvider = serviceProvider;

        for (int i = 0; i < INITIAL_BULLETS; i++) {
            entities.offer(create());
        }
    }

    /**
     * Create a Bullet Entity from the Pool.
     *
     * @return an entity from the pool
     */
    @Override
    protected Entity create() {
        TextureManager textureManager = (TextureManager) serviceProvider.get(TextureManager.class);

        SpriteComponent spriteComponent = new SpriteComponent(new Sprite(textureManager.get("bullets.png:example_bullet")));

        SpeedComponent speedComponent = new SpeedComponent(5);

        PooledComponent pooledComponent = new PooledComponent(this.getClass());

        return World.createEntity(spriteComponent, speedComponent, pooledComponent);
    }
}
