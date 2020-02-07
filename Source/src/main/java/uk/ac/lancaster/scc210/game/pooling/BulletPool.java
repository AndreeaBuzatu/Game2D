package uk.ac.lancaster.scc210.game.pooling;

import uk.ac.lancaster.scc210.engine.content.TextureManager;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.engine.service.ServiceProvider;
import uk.ac.lancaster.scc210.game.prototypes.BulletPrototype;

/**
 * Pool which stores bullets.
 */
public class BulletPool extends Pool {
    // Who cares if it's static? I don't
    private static final int INITIAL_BULLETS = 100;

    /**
     * Instantiate a BulletPool
     *
     * @param serviceProvider used to access the PoolService
     */
    public BulletPool(ServiceProvider serviceProvider) {
        super(INITIAL_BULLETS, new BulletPrototype((TextureManager) serviceProvider.get(TextureManager.class)));

        for (int i = 0; i < INITIAL_BULLETS; i++) {
            entities.offer(create());
        }
    }
}
